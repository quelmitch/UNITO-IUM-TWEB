const express = require('express')
const createError = require('http-errors')
const {engine} = require('express-handlebars')
const path = require('path');
var cookieParser = require('cookie-parser'); //TODO:
var logger = require('morgan'); // TODO:

const app = express()


// Mount Routes
const renderRoute = require(path.join(__dirname, './routes/render/index.js'))
const apiRoute = require(path.join(__dirname, './routes/api/v1/v1.js'))
app.use('/', renderRoute)
//app.use('/api', apiRoute)


// view engine setup
app.set('views', path.join(__dirname, 'views'))
app.engine('hbs', engine({
    extname: '.hbs',
    defaultLayout: 'layout',
    layoutsDir: path.join(__dirname, 'views/layouts'),
    partialsDir: path.join(__dirname, 'views/partials'),
}))
app.set('view engine', 'hbs')

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// catch 404 and forward to error handler
app.use((next) => {
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
