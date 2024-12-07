const express = require('express');
const discover = express.Router();

discover.get('/', (req, res) => {
    res.json({ movies: [] });
})

module.exports = discover;