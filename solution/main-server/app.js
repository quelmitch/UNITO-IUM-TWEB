const express = require('express')
const createError = require('http-errors')
const {engine} = require('express-handlebars')
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

const apiRoute = './routes/api/'
const renderRoute = './routes/render'

//const discoverRenderRouter = require(path.join(__dirname, renderRoute, 'discover'))
//const movieRenderRouter = require(path.join(__dirname, renderRoute, 'movie'))
//const oscarsRenderRouter = require(path.join(__dirname, renderRoute, 'oscars'))
//const oscarRenderRouter = require(path.join(__dirname, renderRoute, 'oscar'))

const discoverApiRouter = require(path.join(__dirname, apiRoute, 'discover/router'))
const movieApiRouter = require(path.join(__dirname, apiRoute, 'movie/router'))
//const oscarsApiRouter = require(path.join(__dirname, apiRoute, 'oscars/router'))
//const oscarApiRouter = require(path.join(__dirname, apiRoute, 'oscar/router'))

const app = express()

//app.use('/discover', discoverRenderRouter)
//app.use('/movie', movieRenderRouter)
//app.use('/oscars', oscarsRenderRouter)
//app.use('/oscar', oscarRenderRouter)

app.use('/api/discover', discoverApiRouter)
app.use('/api/movie', movieApiRouter)
//app.use('/api/oscars', oscarsApiRouter)
//app.use('/api/oscar', oscarApiRouter)

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
