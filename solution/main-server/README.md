# README for MovieDB Main Server

## Overview

The **Main Server** is the central component of the MovieDB platform, built using **Express.js**. It serves two primary purposes:
1. **Frontend Service**: Renders dynamic web pages using **Handlebars.js**.
2. **REST API**: Provides endpoints to communicate with the `postgre-server` (static data) and `mongo-server` (dynamic data) to offer comprehensive movie-related information to clients.

MovieDB is a dynamic platform that integrates multiple datasets to create a rich resource for cinephiles. 
It enables users to explore movies, Oscars, and reviews through intuitive search and filtering options.

Access the website at:
- MovieDB: [http://localhost:3000](http://localhost:3000).

## API Documentation

Access the REST API documentation at:  
- Swagger UI: [http://localhost:3000/api-docs/](http://localhost:3000/api-docs/).

## Key Features

### Frontend Service
- **Dynamic Pages**: Uses Handlebars.js to render responsive and data-driven pages.
- **Search and Filters**: Users can search for movies by title and apply advanced filters such as genre, release year, cast, or characters. Filtering is also available in the Review section.
- **Chat Feature**: Real-time chat rooms for discussions on movies, Oscars, and reviews.

### REST API
- Provides structured endpoints under `/api/v1` for accessing movie metadata, genres, languages, Oscars, reviews, and countries.
- Handles requests by delegating resource-heavy tasks to:
    - **PostgreSQL** (via `postgre-server`) for structured and static data like detailed movie information and Oscars.
    - **MongoDB** (via `mongo-server`) for dynamic data such as reviews and user feedback.

## Project Structure

```
main-server/
├── bin/
├── config/
├── public/
│   ├── images/
│   ├── scripts/
│   └── stylesheets/
├── routes/
│   ├── pages/
│   ├── utils/
│   ├── api/
│   │  └── v1/
│   │      ├── domain/
│   │      │   ├── router.js
│   │      │   └── service.js
│   │      └── v1.js
│   └── index.js
├── services/
│   └── chat/
├── app.js
└── views/
    ├── layouts/
    ├── pages/
    └── partials/
```

## Technology Stack

- **Express.js**: Server framework for building REST APIs and serving dynamic content.
- **Handlebars.js**: Template engine for dynamic web pages.
- **Axios**: For API communication between servers.
