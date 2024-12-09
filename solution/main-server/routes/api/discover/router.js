const express = require('express');
const axios = require('axios');
const discover = express.Router();

discover.get('/', (req, res) => {
    // TODO
    axios.get(`http://localhost:8081/movie/${req.params.movie_id}/reviews`, {
        params: req.query
    })
        .then((response) => {
            res.json(response.data);
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Errore nella comunicazione con il server Spring Boot' });
        })
    res.json(null)
})

module.exports = discover;