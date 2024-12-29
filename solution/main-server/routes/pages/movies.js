const express = require('express');
const axios = require("axios");
const { thisServer } = require('../../config/server')
const router = express.Router();

router.get('/', (req, res) => {
    const page = parseInt(req.query.page) || 1;
    const limit = parseInt(req.query.limit) || 20;

    const offset = (page-1) * limit;

    axios.get(`${thisServer}/api/v1/movie/filter?responseType=DTO&limit=${limit}&offset=${offset}`)
        .then((response) => {
            res.render('pages/movies', {
                title: 'Movies',
                movies: response.data,
                currentPage: page,
                nextPage: page + 1,
                prevPage: page === 1 ? null : page - 1,
                limit: limit,
            })
        })
        .catch((error) => {}) // TODO

})

module.exports = router;
