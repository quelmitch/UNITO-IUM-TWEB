const swaggerJsdoc = require('swagger-jsdoc');

const swaggerSpec = swaggerJsdoc({
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'Movie Reviews API',
            version: '1.0.0',
            description: 'API for querying Rotten Tomatoes Reviews from a MongoDB database.',
        },
        servers: [
            {url: 'http://localhost:3001', description: 'Local server'},
        ],
    },
    apis: ['./routes/*.js'], // TODO fix for new structure
});

module.exports = swaggerSpec;
