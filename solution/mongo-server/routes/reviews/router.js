const express = require('express');
const router = express.Router();
const Model = require("./schema");

const { validateFilters, buildQuery, cleanResults } = require("./service");
const { getPaginatedResults } = require('@utils/paginationHelper');
const { ApiError } = require("@utils/errorHandler");

router.get('/filter', (req, res, next) => {
    // Step 1: Validate filters
    validateFilters(req.query);

    // Step 2: Build query from filters
    const query = buildQuery(req.query);

    // Step 3: Execute query
    getPaginatedResults(Model, query, req.query)
        .then((result) => {
            if (!result.totalPages)
                throw ApiError.notFound("No reviews found matching the query"); // TODO: should return just the empty array or 404?

            res.json(cleanResults(result));
        })
        .catch((err) => {
            // delegate to the next middleware (error handler)
            next(err);
        })
})

module.exports = router
