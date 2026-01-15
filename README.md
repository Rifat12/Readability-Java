# Readability

A text readability analyzer that calculates reading level scores using established mathematical formulas.

## How It Works

All readability scores are computed locally using deterministic algorithms—no external APIs, LLMs, or third-party services are involved. The calculations rely on linguistic metrics (word count, sentence count, syllable count, character count) that are processed entirely within the application. This means:

- **Offline capable** - Works without internet connectivity
- **Predictable results** - Same input always produces the same output
- **No data leaves your system** - Text is analyzed locally and optionally stored in the local database
- **No API keys required** - No external service dependencies

## Scores

Each formula outputs a U.S. grade level estimate (e.g., 8.0 ≈ 8th grade reading level):

- **ARI** - Automated Readability Index (characters per word, words per sentence)
- **FK** - Flesch-Kincaid Grade Level (syllables per word, words per sentence)
- **SMOG** - Simple Measure of Gobbledygook (polysyllabic word count)
- **CL** - Coleman-Liau Index (letters per 100 words, sentences per 100 words)

## Run

```bash
./mvnw spring-boot:run
```

Open http://localhost:8080

## API

**Analyze text**

```bash
POST /api/readability
Content-Type: application/json

{"text": "Your text here."}
```

**Get history**

```bash
GET /api/readability/history
```

## Tech

- Java 17+
- Spring Boot 3.2
- H2 (in-memory database)
