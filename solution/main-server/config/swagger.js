const swaggerJsdoc = require('swagger-jsdoc');

const swaggerSpec = swaggerJsdoc({
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'Public API',
            version: '1.0.0',
            description: 'API for querying data from Spring Boot and Express JS (Mongoose) Servers.',
        },
        servers: [
            {url: 'http://localhost:3000', description: 'Local server'},
        ],
    },
    apis: ['./routes/api/v1/*/*.js'],
});

module.exports = swaggerSpec;
