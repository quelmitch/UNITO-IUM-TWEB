const express = require('express');
const axios = require("axios");
const { thisServer } = require('../../config/server')
const router = express.Router();

router.get('/:id', (req, res) => {
    const movie_id = req.params.id;
    if (!movie_id)
        res.status(400).send('Bad Request')

    axios.get(`${thisServer}/api/v1/movie/${movie_id}`)
        .then((response) => {
            res.render('pages/movie-detail', {
                title: response.data.title || 'Not Found',
                movie: response.data
            })
            console.log(response.data)
        })
        .catch((error) => {}) // TODO

})

module.exports = router;
