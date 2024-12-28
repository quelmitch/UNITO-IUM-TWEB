const express = require('express');

// TODO Merge render and API routes

const homeRouter = require('./home');
const moviesRouter = require('./movies');
const oscarsRouter = require('./oscars');
const reviewsRouter = require('./reviews');

const router = express.Router()
    .use('/', homeRouter)
    .use('/movies', moviesRouter)
    .use('/oscars', oscarsRouter)
    .use('/reviews', reviewsRouter)

module.exports = router;
