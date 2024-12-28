const express = require('express');

const movies = require('./movies/router')

const router = express.Router()
    .use('/movie', movies);

module.exports = router;