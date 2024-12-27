var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

const database = require('./databases/database');
const swaggerUi = require('swagger-ui-express');
const swaggerSpec = require('./swagger');

var indexRouter = require('./routes/index');

var app = express();

// Serve Swagger UI at the `/api-docs` endpoint
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);

module.exports = app;
