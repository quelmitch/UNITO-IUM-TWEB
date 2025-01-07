const express = require('express');

const movies = require('./movies/router')
const reviews = require('./reviews/router')
const oscars = require('./oscars/router')

const router = express.Router()
    .use('/movie', movies)
    .use('/oscar', oscars)
    .use('/review', reviews);

module.exports = router;
