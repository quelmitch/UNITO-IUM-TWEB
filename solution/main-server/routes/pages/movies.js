const express = require('express');
const axios = require("axios");
const { thisServer } = require('../../config/server')
const router = express.Router();

router.get('/', (req, res) => {
    axios.get(`${thisServer}/api/v1/movie/filter?responseType=DTO`)
        .then((response) => {
            console.log(response.data)
            res.render('pages/movies', { title: 'Movies', movies: response.data })
        })
        .catch((error) => {}) // TODO

})

module.exports = router;
