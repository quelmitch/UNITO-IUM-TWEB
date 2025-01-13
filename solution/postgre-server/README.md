# PostgreSQL Server

## Overview
The PostgreSQL Server is a core microservice within the MovieDB project, responsible for managing and serving static data, such as movies and Oscar-related information. This server is implemented in **Spring Boot** with **JPA** and leverages **PostgreSQL** as the database engine. The design adheres to a **domain-driven structure**, ensuring modularity and clarity.

## API Documentation
This server is extensively documented using **Swagger** for API routes and **JavaDocs** for internal code documentation. Developers can quickly access the Swagger UI and JavaDocs locally via the following links:

- Swagger UI: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
- JavaDocs: [http://localhost:8080/docs](http://localhost:8080/docs)

## Project Structure
The project follows a **domain-driven design**, with each domain encapsulating its own responsibilities:

```
postgre-server/
├── java/
│   └── org.unito.postgreserver/
│       ├── <<domain>>/
│       │   ├── api/
│       │   │   ├── <<domain>>Controller.java
│       │   │   ├── <<domain>>Repository.java
│       │   │   ├── <<domain>>Service.java
│       │   ├── dto/
│       │   │   ├── <<domain>>BasicDTO.java
│       │   │   ├── <<domain>>FilterDTO.java
│       │   ├── model/
│       │   │   ├── <<domain>>.java
│       │   │   ├── <<domain>>Type.java
│       ├── utils/
│       │   ├── GenericFilterDTO.java
│       │   ├── ServiceCommon.java
│       │   ├── SpecificationUtility.java
│       └── PostgreServerApplication.java
└── resources/
    ├── application.properties
    ├── application-dev.properties
    └── data.sql
```

### Domain Packages
1. **`api`**: Contains controllers, services, and repositories.
2. **`dto`**: Defines Data Transfer Objects, including:
   - Filter DTOs (e.g., `MovieFilterDTO`) for capturing dynamic URI parameters.
   - Specialized DTOs (e.g., `MovieBasicDTO`) for customized entity views.
3. **`model`**: Houses JPA entities and marker interfaces (e.g., `MovieType`) for type safety.

### Utility Package
The `utils` package provides reusable components for simplifying common operations:

- **`GenericFilterDTO`**: Handles generic filters like pagination, sorting, and response types.
- **`ServiceCommon`**: Contains shared methods across services.
- **`SpecificationUtility`**: Offers generic methods for constructing dynamic JPA Specifications. For example:
```java
 public static <T> Specification<T> equalsTo(String field, Comparable<?> value) {
      return (root, _, criteriaBuilder) ->
         value == null ? null : criteriaBuilder.equal(root.get(field), value);
}
```

These utilities empower the server to dynamically build complex queries based on user input without requiring explicit SQL.

### Data Access
The server primarily employs **JPA Specifications** with `Pageable` for all database queries, utilizing the default `findAll` method of JPA repositories. 
Explicit SQL queries are avoided, except for queries where the DISTINCT is required, that is not applicable with JPA Specifications.

## Technology Stack and Dependencies
The server leverages the following technologies:

- **Languages**: Java 23
- **Frameworks**: Spring Boot, Spring JPA
- **Database**: PostgreSQL 17.x
- **Utilities**: Lombok for boilerplate reduction

Dependencies are managed using Gradle.

## Pagination and Filtering
Most API endpoints support advanced filtering and pagination using `GenericFilterDTO` and `SpecificationUtility`. Below is an example of a filtering method for the `Movie` entity:

```java
 public Map<String, Object> getMovieWithFilter(GenericFilterDTO genericFilter, MovieFilterDTO movieFilter) {
      Specification<Movie> specification = combineWithAnd(List.of(
              like("title", movieFilter.getTitle()),
              greaterThan("runtime", movieFilter.getRuntimeGT()),
              lessThan("runtime", movieFilter.getRuntimeLT()),
              greaterThan("releaseYear", movieFilter.getReleaseYearGT()),
              lessThan("releaseYear", movieFilter.getReleaseYearLT()),
              greaterThan("rating", movieFilter.getRatingGT()),
              lessThan("rating", movieFilter.getRatingLT()),
              like("actors", "name", movieFilter.getActor()),
              like("actors", "role", movieFilter.getCharacter()),
              like("crew", "name", movieFilter.getCrew()),
              equalsTo("genres", "genre", movieFilter.getGenre()),
              equalsTo("countries", "country", movieFilter.getProductionCountry()),
              equalsTo("languages", "language", movieFilter.getLanguage()),
              equalsTo("studios", "studio", movieFilter.getStudio())
      ));

      Pageable pageable = setPageable(genericFilter, movieFilter.getSortBy());
      Page<Movie> moviePage = movieRepository.findAll(specification, pageable);
      List<? extends MovieType> movies = mapMovies(moviePage.getContent(), genericFilter.getResponseType());
   
      return buildResponse(genericFilter, moviePage.getTotalPages(), movies);
 }
```
