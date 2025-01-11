const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const swaggerUi = require('swagger-ui-express');

const database = require('@config/database');
const swaggerSpec = require('@config/swagger');
const indexRouter = require('./modules/routes');

const app = express();

// serve Swagger UI at the `/api-docs` endpoint
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);

app.use((req, res) => {
    res.status(404).json({
        status : 404,
        message: 'Not Found'
    })
})
app.use((err, req, res, next) => {
    res.status(err.status || 500).json({
        status : err.status || 500,
        message: err.response?.statusText || 'Internal Server Error'
    })
})

module.exports = app;
