# Media Service

## 🎯 Purpose
The **Media Service** is the core content catalog. It manages movies, TV shows, genres, categories, and the metadata associated with the streaming platform's library. 

## 🧑‍💻 Developer Guidelines
1. **API Responses**: ALL endpoints MUST return responses wrapped in the `ApiResponse<T>` class located in `dto/ApiResponse.java`. 
2. **Exceptions**: Throw `ResourceNotFoundException`, `BadRequestException`, or `DuplicateResourceException` when business rules are violated. The `GlobalExceptionHandler` will catch these automatically.
3. **Database**: We are using PostgreSQL. The credentials and port (`5434`) are injected via the Config Server. Do not hardcode them.
4. **Validation**: Ensure you validate user input on creation/update DTOs using standard `jakarta.validation` annotations.

---

## 🏗️ Data Models (Entities & Enums)

You need to implement the following entities and enums.

### 1. `Genre` (Enum)
Categorizes the content by narrative style.
* `ACTION`, `COMEDY`, `DRAMA`, `SCIFI`, `DOCUMENTARY`, `THRILLER`, `HORROR`

### 2. `Category` (Enum)
Categorizes the format of the content.
* `MOVIE`, `SERIES`, `DOCUMENTARY_FILM`

### 3. `Media` (Entity)
The core content model.
* `id` (UUID or Long, Primary Key)
* `title` (String, Not Null)
* `description` (String, length > 10, Not Null)
* `releaseYear` (Integer)
* `duration` (Integer, length in minutes)
* `genre` (Genre Enum)
* `category` (Category Enum)
* `rating` (Double, 0.0 to 10.0)
* `createdAt` (LocalDateTime)
* `updatedAt` (LocalDateTime)

---

## 🛣️ Required Endpoints (CRUD & Search)
You must implement a Controller with the following operations:
* `POST /api/v1/media` - Add new media
* `GET /api/v1/media/{id}` - Get media by ID
* `GET /api/v1/media` - Get all media (Pagination highly recommended)
* `PUT /api/v1/media/{id}` - Update media metadata
* `DELETE /api/v1/media/{id}` - Delete a media item
* `GET /api/v1/media/search?title={title}&genre={genre}` - Search/Filter media by title or genre
