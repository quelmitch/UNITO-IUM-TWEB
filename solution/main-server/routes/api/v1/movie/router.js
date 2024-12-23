const express = require('express');
const axios = require('axios');
const movie = express.Router();

movie

movie.get('/:movie_id', async (req, res) => {
    axios.get(`http://localhost:8081/movie/${req.params.movie_id}`)
        .then((response) => {
            res.json(response.data);
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Errore nella comunicazione con il server Spring Boot' });
        })
})

module.exports = movie;