const express = require('express');
const axios = require("axios");
const {thisServer} = require('@config/server')
const {fromObjectToUri} = require("@routes-utils/common-service");

const router = express.Router();

router.get('/', (req, res, next) => {
    const filters = req.query;

    Promise.all([
        axios.get(`${thisServer}/api/v1/genre/all`),
        axios.get(`${thisServer}/api/v1/language/all`),
        axios.get(`${thisServer}/api/v1/country/all`),
        axios.get(`${thisServer}/api/v1/movie/filter?responseType=BASIC&${fromObjectToUri(filters)}`)
    ])
        .then(([genreResponse, languageResponse, countryResponse, movieResponse]) => {
            const genres = genreResponse.data;

            const languages = languageResponse.data;
            const countries = countryResponse.data;

            const totalPages = movieResponse.data.totalPages;
            const page = movieResponse.data.page;
            const limit = movieResponse.data.limit;

            delete filters.page;
            res.render('pages/movies', {
                title: 'Movies',
                movies: movieResponse.data.content,
                pagingOptions: {
                    page: page,
                    nextPage: page + 1,
                    prevPage: page - 1,
                    totalPages: totalPages === 0 ? 0 : totalPages - 1,
                    limit: limit,
                },
                filters: {
                    obj: filters,
                    uri: fromObjectToUri(filters),
                },
                genres: genres,
                languages: languages,
                countries: countries,
            });
        })
        .catch((error) => next(error))

})

module.exports = router;
