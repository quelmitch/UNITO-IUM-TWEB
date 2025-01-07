const express = require('express');
const axios = require("axios");
const { thisServer } = require('@config/server')
const {fromObjectToUri} = require("@routes-utils/common_service");

const router = express.Router();

router.get('/', (req, res) => {
    const filters = req.query;

    axios.get(`${thisServer}/api/v1/movie/filter?responseType=BASIC&${fromObjectToUri(filters)}`)
        .then((response) => {
            const page = response.data.page;
            const limit = response.data.limit;
            const totalPages = response.data.totalPages;
            delete filters.page;
            res.render('pages/movies', {
                title: 'Movies',
                movies: response.data.content,
                pagingOptions: {
                    page: page,
                    nextPage: page + 1,
                    prevPage: page - 1,
                    totalPages: totalPages-1,
                    limit: limit,
                },
                filters: {
                    obj : filters,
                    uri: fromObjectToUri(filters),
                },
            })
        })
        .catch((error) => {}) // TODO

})

module.exports = router;
