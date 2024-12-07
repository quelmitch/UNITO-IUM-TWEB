const express = require('express');
const movie = express.Router();

movie.get('/', (req, res) => {
    res.json({ });
})

module.exports = movie;