const express = require('express');

const movies = require('./movies/router')
const reviews = require('./reviews/router')
const oscars = require('./oscars/router')
const genres = require('./genres/router')
const languages = require('./languages/router')
const countries = require('./countries/router')

const router = express.Router()
    .use('/movie', movies)
    .use('/oscar', oscars)
    .use('/review', reviews)
    .use('/genre', genres)
    .use('/language', languages)
    .use('/country', countries)

module.exports = router;
