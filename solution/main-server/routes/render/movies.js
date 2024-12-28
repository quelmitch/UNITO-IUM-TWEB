const express = require('express');
const axios = require("axios");
const { springbootServer} = require("../../config/config");
const router = express.Router();

router.get('/', (req, res) => {
    let data = {}
    axios.get(`api/movie/filter`)
        .then((response) => {
            data = response.data;
        })
    res.render('pages/reviews', { title: 'Movies', movies: data })
})

module.exports = router;
