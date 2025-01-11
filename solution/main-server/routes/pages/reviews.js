const express = require('express');
const axios = require("axios");
const { thisServer } = require('@config/server')
const { fromObjectToUri } = require('@routes-utils/common_service')

const router = express.Router();

router.get('/', (req, res, next) => {
    // throw new Error()

    const filters = req.query;

    axios.get(`${thisServer}/api/v1/review/filter?${fromObjectToUri(filters)}`)
        .then((response) => {
            const totalPages = response.data.totalPages - 1;
            if (totalPages < 0)
                return res.render('pages/error', {
                    error_status: 404,
                    error_name: 'Not Found',
                    error_message: 'No reviews found matching filters.',
                    error_redirect: '/reviews',
                });

            const page = response.data.page;
            const limit = response.data.limit;

            const typeValues = ["Fresh", "Rotten"]
            const topCriticValues = ["True", "False"]

            delete filters.page;
            res.render('pages/reviews', {
                title: 'Reviews',
                reviews: response.data.content,
                types: typeValues,
                topCritics: topCriticValues,
                pagingOptions: {
                    page: page-1,
                    nextPage: page + 1,
                    prevPage: page - 1,
                    totalPages: totalPages,
                    limit: limit,
                },
                filters: {
                    obj : filters,
                    uri: fromObjectToUri(filters),
                },
            })
        })
        .catch((error) => next(error))
});

module.exports = router;
