const express = require('express');

const movies = require('./movies/router')
const reviews = require('./reviews/router')

const router = express.Router()
    .use('/movie', movies)
    .use('/review', reviews);

module.exports = router;
