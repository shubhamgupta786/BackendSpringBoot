# Spring Boot Cache API

This project provides a simple caching API built with Spring Boot. It allows you to store, retrieve, and delete key-value pairs with a maximum cache size.

## Endpoints

*   **POST /cache**: Stores a key-value pair in the cache.
    *   Request Body:

        ```
        {
            "key": "string",
            "value": "string"
        }
        ```

    *   Success Response:
        *   Status Code: 201 Created
        *   Body: `Value stored`
    *   Error Response (Cache Full):
        *   Status Code: 400 Bad Request
        *   Body: `Error: Cache is full`

*   **GET /cache/{key}**: Retrieves a value from the cache.
    *   Path Variable: `key` (the key to retrieve)
    *   Success Response:
        *   Status Code: 200 OK
        *   Body: The value associated with the key.
    *   Error Response (Key Not Found):
        *   Status Code: 404 Not Found

*   **DELETE /cache/{key}**: Deletes a key-value pair from the cache.
    *   Path Variable: `key` (the key to delete)
    *   Success Response:
        *   Status Code: 200 OK
    *   Error Response (Key Not Found):
        *   Status Code: 404 Not Found

## Usage

1.  **Clone the repository:**

    ```
    git clone <repository-url>
    ```

2.  **Build the project:**

    ```
    cd <project-directory>
    mvn clean install
    ```

3.  **Run the application:**

    ```
    mvn spring-boot:run
    ```

    The application will start on port 8080 by default.

## Testing with Postman

1.  **Open Postman.**

2.  **Test the `POST /cache` endpoint:**
    *   Select `POST` method.
    *   Enter the URL: `http://localhost:8080/cache`.
    *   In the "Body" tab, select "raw" and "JSON" and enter a key-value pair:

        ```
        {
            "key": "key1",
            "value": "value1"
        }
        ```

    *   Send the request.

3.  **Test the `GET /cache/{key}` endpoint:**
    *   Select `GET` method.
    *   Enter the URL: `http://localhost:8080/cache/key1`.
    *   Send the request.

4.  **Test the `DELETE /cache/{key}` endpoint:**
    *   Select `DELETE` method.
    *   Enter the URL: `http://localhost:8080/cache/key1`.
    *   Send the request.

## Cache Configuration

*   **Maximum Size**: The cache has a fixed maximum size of 10 entries.  Once this limit is reached, adding a new key will fail and the API will return an error. Updating existing keys will still work.  This value is defined in the `CacheService` class.
*   **No Expiration**:  The current implementation does not support expiration.
*   **Thread Safety:** The underlying `LinkedHashMap` is not thread-safe.  For production environments with concurrent access, consider using a `ConcurrentHashMap` or adding synchronization.

## Error Handling

The API returns appropriate HTTP status codes for different scenarios:

*   `201 Created`:  Successfully stored a new key-value pair.
*   `200 OK`:  Successfully retrieved or deleted a key-value pair.
*   `400 Bad Request`:  Cache is full (when attempting to POST a new key).
*   `404 Not Found`:  Key not found (when attempting to GET or DELETE).

## Further Improvements

*   Implement cache expiration using a scheduled task or a library like Caffeine.
*   Add metrics and monitoring.
*   Use a more robust caching solution like Redis or Memcached for distributed caching.
*   Implement proper authentication and authorization.
*   Add thread safety to the CacheService.
