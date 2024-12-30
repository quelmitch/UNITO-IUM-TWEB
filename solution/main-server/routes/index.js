const express = require('express');

const homeRouter = require('./pages/home');
const moviesRouter = require('./pages/movies');
const oscarsRouter = require('./pages/oscars');
const reviewsRouter = require('./pages/reviews');
const movieDetailRouter = require('./pages/movie-detail');
const apiV1 = require('./api/v1/v1');

const router = express.Router()
    .use('/api/v1/', apiV1)
    .use('/', homeRouter)
    .use('/movies', moviesRouter)
    .use('/oscars', oscarsRouter)
    .use('/reviews', reviewsRouter)
    .use('/movie', movieDetailRouter)

module.exports = router;
