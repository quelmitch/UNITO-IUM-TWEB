const express = require('express');
const {engine} = require('express-handlebars');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');

const swaggerUi = require('swagger-ui-express');
const swaggerSpec = require('@config/swagger');

const app = express()

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

// Middleware
app.use(logger('dev')) // HTTP request logger
app.use(express.json()) // Parse JSON request bodies
app.use(express.urlencoded({ extended: false })) // Parse form data
app.use(cookieParser()) // Parse cookies from requests
app.use(express.static(path.join(__dirname, 'public'))) // Serve static files from public directory

// View Engine Setup
const { navLinks, helpers } = require(path.join(__dirname,'./config/handlebars'))
app.set('views', path.join(__dirname, 'views'))
app.engine(
    'hbs',
    engine({
        extname: '.hbs',
        defaultLayout: 'layout',
        layoutsDir: path.join(__dirname, 'views/layouts'),
        partialsDir: [
            path.join(__dirname, 'views/partials'),
            path.join(__dirname, 'views/partials/movies'),
            path.join(__dirname, 'views/partials/oscars'),
            path.join(__dirname, 'views/partials/reviews'),
            path.join(__dirname, 'views/partials/ui'),
            path.join(__dirname, 'views/partials/filters'),
        ],
        helpers: helpers,
    })
)
app.set('view engine', 'hbs')
app.use((req, res, next) => {
    res.locals.navLinks = navLinks
    res.locals.currentPath = req.path
    next()
})


// Mount Routes
const routes = require(path.join(__dirname, './routes/index'))
app.use('/', routes)

// Error Handling
app.use((req, res) => {
    res.status(404).render('pages/error', {
        error_status: 404,
        error_name: 'Not Found',
        error_message: 'This page does not exist. Return to the Homepage.',
        error_redirect: '/',
    });
})
app.use((err, req, res, next) => {
    console.log(err)
    res.status(err.status || 500).render('pages/error', {
        error_status: err.status || 500,
        error_name: err.response.statusText,
        error_message: 'Return to the Homepage.',
        error_redirect: '/',
    })
})

module.exports = app;
