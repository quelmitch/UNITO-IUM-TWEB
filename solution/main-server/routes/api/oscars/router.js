const express = require('express');
const oscars = express.Router();

oscars.get('/', (req, res) => {
    res.json({ oscars: [] });
})

module.exports = oscars;