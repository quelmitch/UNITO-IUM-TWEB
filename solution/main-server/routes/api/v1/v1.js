const express = require('express');

const movies = require('./movies/router')
const reviews = require('./reviews/router')
const oscars = require('./oscars/router')
const genres = require('./genres/router')
const languages = require('./languages/router')

const router = express.Router()
    .use('/movie', movies)
    .use('/oscar', oscars)
    .use('/review', reviews)
    .use('/genre', genres)
    .use('/language', languages)

module.exports = router;
