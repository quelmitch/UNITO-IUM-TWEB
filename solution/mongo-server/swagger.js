const swaggerJsdoc = require('swagger-jsdoc');

const options = {
    definition: {
        openapi: '3.0.0', // Specify OpenAPI version
        info: {
            title: 'Movie Reviews API',
            version: '1.0.0',
            description: 'API for querying movie reviews from a MongoDB database.',
        },
        servers: [
            { url: 'http://localhost:3000', description: 'Local server' },
        ],
    },
    apis: ['./routes/*.js'], // Path to the API docs (add comments in your route files)
};

const swaggerSpec = swaggerJsdoc(options);
module.exports = swaggerSpec;
