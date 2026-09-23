# Movie Streaming App — Ktor Backend

For KMP Application (Android & IOS)


# Video Source & Licensing

The sample remote video used for testing is:

**Duck and Cover (1951)**

Source:

```text
Internet Archive
```

Remote video:

```text
https://archive.org/download/ArcherProductionsInc/DuckandC1951.mp4
```

The video is identified by available archival sources as public domain / having no known copyright in the United States.

The video is used as a legally usable remote streaming test asset for this assignment.

The video is **not included in the application bundle** and is streamed from the remote Internet Archive URL.

For additional media, the rights and licensing terms must be verified individually before adding the media to the application.





# Example Request Flow

When the KMP application requests:

```http
GET /api/movies
```

the flow is:

```text
KMP Client
    │
    │ GET /api/movies
    ▼
Ktor Routing
    │
    ▼
MoviesRepository
    │
    ├──────────────► OMDb API
    │                    │
    │                    ▼
    │              Movie Metadata
    │
    └──────────────► Mapping Video URL
                         │
                         ▼
                    Remote Video URL
    │
    ▼
Application Movie Model
    │
    ▼
JSON Response
    │
    ▼
KMP Client
```

---

# Running the Backend

## Requirements

* JDK 21 or compatible JDK
* Gradle
* Internet connection
* OMDb API key

---

## Configure API Key

Set the environment variable:

```bash
export OMDB_API_KEY="your_api_key"
```

---

## Run from IntelliJ IDEA

1. Open the Ktor project.
2. Configure the `OMDB_API_KEY` environment variable.
3. Run the Ktor application.
4. The server starts on:

```text
http://localhost:8080
```

---

## Run with Gradle

```bash
./gradlew run
```

---

# Testing the API

### Get all movies

```bash
curl http://localhost:8080/api/movies
```

### Get movie by ID

```bash
curl http://localhost:8080/api/movies/duck-and-cover
```

The API can also be tested using:

* Postman
* Browser for GET endpoints
* The KMP application

---

# KMP Integration

The KMP application communicates only with the Ktor backend for movie data.

```text
Android ──────┐
              │
iOS ──────────┼──► Ktor Backend ───► OMDb
              │
              └────────────────────► Video Source
```

The KMP client does not directly call OMDb for the movie catalogue.

This keeps:

* API keys on the backend
* External API implementation on the backend
* Movie mapping on the backend
* Video source configuration on the backend

The Android and iOS applications consume the same backend API.

---

# Security

The following security considerations are applied:

* OMDb API key is stored outside source code.
* API keys are not included in the mobile application.
* API keys should not be committed to Git.
* Raw external API errors are not returned to clients.
* Internal stack traces are not exposed through API responses.
* Remote video URLs are controlled by the backend.
* Only legally usable media sources should be added.

---

# Limitations

### 1. Sample video availability

The application uses a publicly available remote video for demonstration purposes.

### 2. External API dependency

Movie metadata depends on the availability and response of the OMDb API.

If OMDb is unavailable, the backend returns a controlled API error instead of exposing the underlying exception.

### 3. Video Link

The sample video “Duck and Cover” (1951) is streamed remotely from the Internet Archive.

Source: Internet Archive
Video URL: https://archive.org/download/ArcherProductionsInc/DuckandC1951.mp4

The video is identified as public domain / no known copyright and is used in this assignment as a legally usable remote streaming test asset.

It is streamed directly from the remote Internet Archive URL.


### Production deployment

The current backend is designed for local development and assignment demonstration.


# Assignment Requirements Coverage

| Requirement                           | Implementation |
| ------------------------------------- | -------------- |
| Ktor backend                          | Implemented    |
| `GET /api/movies`                     | Implemented    |
| `GET /api/movies/{id}`                | Implemented    |
| External movie API                    | OMDb API       |
| Backend owns external API integration | Yes            |
