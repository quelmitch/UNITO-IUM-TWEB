const express = require('express');
const axios = require('axios');
const movie = express.Router();

movie.get('/:id', async (req, res) => {
    try {
        const response =
            await axios.get(`http://localhost:8081/movie/${req.params.id}`);
        res.json(response.data);
    } catch (error) {
        res.status(500).json({ error: 'Errore nella comunicazione con il server Spring Boot' });
    }
})

module.exports = movie;