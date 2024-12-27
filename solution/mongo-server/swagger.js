const swaggerJsdoc = require('swagger-jsdoc');

const swaggerSpec = swaggerJsdoc({
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'Movie Reviews API',
            version: '1.0.0',
            description: 'API for querying movie reviews from a MongoDB database.',
        },
        servers: [
            {url: 'http://localhost:3000', description: 'Local server'},
        ],
    },
    apis: ['./routes/*.js'], // path to the API docs
});

module.exports = swaggerSpec;
