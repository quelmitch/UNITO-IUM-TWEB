const express = require('express')
const createError = require('http-errors')
const {engine} = require('express-handlebars')
const path = require('path');
var cookieParser = require('cookie-parser'); //TODO:
var logger = require('morgan'); // TODO:

const app = express()

// middleware to set navigation links and the current path for dynamic rendering in views
app.use((req, res, next) => {
    res.locals.navLinks = [
        { path: '/', label: 'Home' },
        { path: '/movies', label: 'Movies' },
        { path: '/oscars', label: 'Oscars' },
        { path: '/reviews', label: 'Reviews' },
    ];
    // stores the current request path
    res.locals.currentPath = req.path;
    next();
});

// Mount Routes
const renderRoute = require(path.join(__dirname, './routes/render/index'))
const apiRoute = require(path.join(__dirname, './routes/api/v1/v1.js'))
app.use('/', renderRoute)
//app.use('/api', apiRoute)

// add a Handlebars helper to simplify comparisons
const hbsHelpers = {
    eq: (a, b) => a === b
};

// view engine setup
app.set('views', path.join(__dirname, 'views'))
app.engine('hbs', engine({
    extname: '.hbs',
    defaultLayout: 'layout',
    layoutsDir: path.join(__dirname, 'views/layouts'),
    partialsDir: path.join(__dirname, 'views/partials'),
    helpers: hbsHelpers,
}));
app.set('view engine', 'hbs')

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// catch 404 and forward to error handler
app.use((req, res, next) => {
    console.log('Request 404 for:', req.originalUrl);
    next(createError(404));
});

// error handler
app.use((err, req, res) => {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
