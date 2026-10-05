# Helpful Hangul

[![CI](https://github.com/samandrews97/Helpful_Hangul/actions/workflows/ci.yml/badge.svg)](https://github.com/samandrews97/Helpful_Hangul/actions/workflows/ci.yml)

A REST API for Korean pronunciation: the 40 basic jamo (the letters of Hangul) and the sound change rules that apply when one syllable's final consonant meets the next syllable's initial consonant.

Korean is not always pronounced as it is written. 학년 is spelled *hak-nyeon* but pronounced *hang-nyeon*, because a final ㄱ followed by ㄴ becomes ㅇ. This API models those rules as data, so a client can ask "what happens when ㄱ is followed by ㄴ?" and get a structured answer.

**Live demo:** https://d1aq0uzqqm175a.cloudfront.net

**Frontend repo:** [Helpful_Hangul_Web](https://github.com/samandrews97/Helpful_Hangul_Web) (React)

## Why I built this

I'm learning Korean, and I built this to solve a problem I kept running into. Hangul is a simple alphabet, but pronunciation isn't simple. As a beginner I could sound out short one-syllable words, hear a native speaker say them and find I'd got them right, which felt great.

With longer words, I'd read them as written, hear them said completely differently, and have no idea why. The answer was sound change rules, where a final consonant changes depending on the syllable that follows it. I wanted a reference that shows what actually happens when two syllables meet.

## Tech stack

- Java 17, Spring Boot 4 (Web MVC, Data JPA)
- PostgreSQL
- JUnit 5, Mockito, MockMvc
- Maven
- Deployed on AWS: EC2 for the API, S3 and CloudFront for the frontend

## API

All endpoints are read-only and return JSON.

| Method | Path | Description |
| --- | --- | --- |
| GET | `/api/jamo` | List all jamo |
| GET | `/api/jamo/{id}` | Get one jamo |
| GET | `/api/jamo/{id}/sound-change-rules` | All rules triggered by this jamo as a final consonant |
| GET | `/api/sound-change-rules` | List all sound change rules |
| GET | `/api/sound-change-rules/{id}` | Get one rule |
| GET | `/api/sound-change-rules/resolve?triggerJamoId=&followingJamoId=` | Find the rule that applies to a pair of jamo |

Status codes:

- `200` with a body when the resource or rule exists
- `204` from `/resolve` when both jamo exist but no rule connects them
- `404` when a jamo or rule id does not exist

### Example

```
GET /api/sound-change-rules/resolve?triggerJamoId=1&followingJamoId=3
```

```json
{
  "id": 1,
  "soundChangeType": "NASALISATION",
  "triggerJamo":   { "id": 1,  "character": "ㄱ", "name": "Giyeok", "romanisation": "g/k" },
  "followingJamo": { "id": 3,  "character": "ㄴ", "name": "Nieun",  "romanisation": "n" },
  "resultingJamo": { "id": 12, "character": "ㅇ", "name": "Ieung",  "romanisation": "ng" }
}
```

Jamo objects are shortened here. The full response also includes the jamo type, manner of articulation, which syllable positions the jamo can occupy, and audio URLs.

## Data model

**Jamo** holds a character, its name and romanisation, its type (consonant or vowel), its manner (plain, aspirated or tense), and which syllable positions it can occupy: initial (choseong), medial (jungseong) or final (jongseong).

**SoundChangeRule** links three jamo: the final consonant that triggers the change, the initial consonant that follows it, and the sound the final consonant becomes. Each rule also has a type, such as nasalisation, liaison, tensing or aspiration.

## Design decisions

- **Rules reference jamo, they do not copy them.** A rule holds three foreign keys to the `jamo` table, so each character's details live in one place.
- **"No rule" is not an error.** Most jamo pairs are pronounced as written. `/resolve` returns `204` for these and keeps `404` for ids that do not exist, so a client can tell "nothing changes" apart from "bad request".
- **Layered structure.** Controllers handle HTTP, services hold the logic, repositories handle persistence. Each layer is tested separately: services with Mockito, controllers with `@WebMvcTest` and MockMvc.
- **Seed data reflects real orthography.** For example ㄸ, ㅃ and ㅉ cannot be final consonants, while ㄲ and ㅆ can.

## Problems I hit

### The site loaded but showed no data

After the first deployment, the site loaded but showed no data. Every test I ran with `curl` against the API passed, which made it confusing.

The cause was mixed content. The frontend was served over HTTPS from CloudFront, but it called the API over plain HTTP on the EC2 instance. Browsers block that silently, and `curl` does not, so my checks could not see the failure.

I fixed it by adding the EC2 instance as a second CloudFront origin and routing `/api/*` to it. The browser now reaches the frontend and the API on one HTTPS origin, which also removed the need for cross-origin requests.

### The app crash-looped on the server

On the EC2 instance the app kept restarting with `relation "jamo" does not exist`. The error came from `DataSeeder`, so it looked like a problem with the seed data, but the tables had never been created.

The cause was a change in PostgreSQL 15. A role that is not the owner can no longer create tables in the `public` schema by default. I had granted the application's role all privileges on the database, but that does not include creating tables in a schema, so Hibernate could not build the schema and the seeder then failed on the missing table.

I fixed it by granting the role access to the schema itself with `GRANT ALL ON SCHEMA public`. It did not happen on my own machine because my local role owns the database.

## Running locally

Requirements: Java 17 or later, and PostgreSQL.

```bash
git clone https://github.com/samandrews97/Helpful_Hangul.git
cd Helpful_Hangul
createdb helpful_hangul
```

The default connection settings are in `src/main/resources/application.properties`. Override them with environment variables to match your own database:

```bash
export SPRING_DATASOURCE_USERNAME=your_user
export SPRING_DATASOURCE_PASSWORD=your_password
./mvnw spring-boot:run
```

The API starts on http://localhost:8080. On first run, Hibernate creates the tables and `DataSeeder` inserts the jamo and rules.

## Tests

```bash
./mvnw test
```

The tests need no database setup. They run against an in-memory H2 database, so they pass on any machine with Java installed. GitHub Actions runs them on every push.

## Deployment

The API runs as a systemd service on an EC2 instance, with PostgreSQL on the same machine. The React frontend is a static build in a private S3 bucket, served through CloudFront. CloudFront also forwards `/api/*` to the EC2 instance, so the browser reaches the frontend and the API on one HTTPS origin.

The instance's security group only accepts API traffic from CloudFront, so the API cannot be reached directly. The database port is not open to the internet.

## Status and roadmap

This is an early version (0.1). All 40 basic jamo are seeded. Sound change rules are currently seeded for ㄱ only.

- [ ] Rules for the remaining final consonants
- [ ] Audio clips for each jamo
- [ ] Versioned schema migrations with Flyway, replacing `ddl-auto=update`

## How this was built

I built this project with Claude Code as a pair programmer, and the commit history reflects that. I designed the data model and the API, and wrote the core logic myself: the entities, the services and the rule resolution. AI assistance covered scaffolding, test setup, debugging, the deployment scripting and documentation. I reviewed every change, and I can explain every design decision in this repo.
