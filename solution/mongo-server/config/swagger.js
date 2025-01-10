const swaggerJsdoc = require('swagger-jsdoc');

const swaggerSpec = swaggerJsdoc({
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'MongoDB API',
            version: '1.0.0',
            description: 'API for querying data from a MongoDB database.',
        },
        servers: [
            {url: 'http://localhost:3001', description: 'Local server'},
        ],
    },
    apis: ['./modules/*/*.router.js'],
});

module.exports = swaggerSpec;
