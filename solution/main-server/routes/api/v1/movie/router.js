const express = require('express');
const axios = require('axios');
const movie = express.Router();

movie.get('/', async (req, res) => {
    axios.get(`http://localhost:8081/movie/$`)
        .then((response) => {
            res.json(response.data);
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Errore nella comunicazione con il server Spring Boot' });
        })
})

module.exports = movie;