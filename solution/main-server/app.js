const express = require('express')
const createError = require('http-errors')
const {engine} = require('express-handlebars')
const path = require('path')
const cookieParser = require('cookie-parser')
const logger = require('morgan')

const app = express()

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
        partialsDir: path.join(__dirname, 'views/partials'),
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
app.use((req, res, next) => {
    // 404 errors
    console.log('Request 404 for:', req.originalUrl);
    next(createError(404));
})
app.use((err, req, res) => {
   // Set error locals for development and production
  res.locals.message = err.message
  res.locals.error = req.app.get('env') === 'development' ? err : {}

    // Render the error page
  res.status(err.status || 500)
  res.render('error')
})

module.exports = app;
