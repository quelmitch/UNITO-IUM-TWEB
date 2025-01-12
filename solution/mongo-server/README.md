# TODO
- fix code snippets
- swagger link

# MongoDB Server

## Overview
The MongoDB Server is a core microservice within the MovieDB project, responsible for managing and serving dynamic data such as movie reviews. This server is implemented in **Node.js** with **Express** and uses **MongoDB** as the database engine. Its design ensures modularity, reusability, and clarity, following a feature-driven folder structure.

## How to import database data
The clean `review` dataset from the data-analysis project can be easily imported into MongoDB using the **Mongodb Database Tools**. <br>
Here is the command line command to load the data. Adjust parameters based on your configuration (The file must be a JSON to correctly import the data types):
```bash
mongoimport --uri="mongodb://localhost:27017" --db=<<DB_NAME>> --collection=reviews --type=json --file=<<path/to/reviews.json>>
```
Alternatively the `review` dataset can also be imported directly using MongoDB Compass.

## API Documentation
This server is extensively documented using **Swagger** for API routes. Developers can access the Swagger UI locally via the following link:

- Swagger UI: [http://localhost:3000/swagger-ui/index.html](http://localhost:3000/swagger-ui/index.html)

## Project Structure
The project follows a **domain-driven structure**, with each domain module encapsulating its own responsibilities:

```
mongo-server/
├── bin/
│   └── www
├── config/
│   ├── database.js
│   └── swagger.js
├── modules/
│   └── reviews/
│       ├── filter.js
│       ├── router.js
│       ├── schema.js
│       └── service.js
├── shared/
│   ├── middleware/
│   │   ├── errorHandler.js
│   │   └── filter-factory.js
│   └── utils/
│       ├── generic-filter.js
│       └── routers-utils.js
├── routes.js
└── app.js
```

### Modules
Encapsulates domain-specific features like `reviews`.
- Each module has:
    - **`<domain>.router.js`**: Defines Express routes for API endpoints.
    - **`<domain>.service.js`**: Contains business logic and interaction with the database.
    - **`<domain>.schema.js`**: Defines Mongoose schemas for MongoDB collections.
    - **`<domain>Filter.js`**: Implements dynamic filtering logic for queries.

### Shared
Contains common code across modules.
- **`middleware`**: Provides reusable middlewares like error handling (`errorHandler.js`) and filtering factories (`filter-factory.js`).
- **`utils`**: Contains utility functions such as `generic-filter.js` for dynamic query construction and `routers-utils.js` for router-specific helpers.

## Technology Stack and Dependencies
The server leverages the following technologies:

- **Languages**: JavaScript
- **Frameworks**: Node.js, Express
- **Database**: MongoDB
- **Utilities**: Mongoose for ODM

Dependencies are managed using **npm**.

## Filtering
The MongoDB server supports advanced filtering and querying using the `filter-factory` middleware that allow to use filter classes to dynamically bind field and values.

```javascript
function parseFilters(FilterClass) {
    return (req, res, next) => {
        try {
            if (FilterClass === GenericFilter)
                req.generic_filters = new FilterClass(req.query);
            else
                req.entity_filters = new FilterClass(req.query);
            next();
        } catch (error) {
            // TODO error handling
            res.status(400).json({error: error.message});
        }
    };
}
```
```javascript
class GenericFilter {
    constructor({page, limit, sortOrder}) {
        this.page = page ? parseInt(page, 10) : 1;
        this.limit = limit ? parseInt(limit, 10) : 10;
        this.sortOrder = sortOrder || 'asc';

        // TODO check error handling
        if (isNaN(this.page) || this.page < 1) throw new Error('Page must be a positive integer'); // TODO < 0 or < 1 ?
        if (isNaN(this.limit) || this.limit < 1) throw new Error('Limit must be a positive integer');
    }
}
```