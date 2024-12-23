const express = require('express');
const oscar = express.Router();

oscar.get('/', (req, res) => {
    res.json({ });
})

module.exports = oscar;