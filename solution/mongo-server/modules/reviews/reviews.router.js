const express = require('express');
const reviewsRouter = express.Router();
const Model = require("./reviews.schema");

const {cleanResults} = require("./reviews.service");
const {ReviewFilter} = require("./ReviewFilter");

const {getPaginatedResults, toPlainObject} = require('@utils/routers.utils');
const {GenericFilter} = require("@utils/GenericFilter");

const {ApiError} = require("@middleware/errorHandler");
const {parseFilters} = require("@middleware/filter-factory");

const genericFilterMiddleware = parseFilters(GenericFilter);
const reviewFilterMiddleware = parseFilters(ReviewFilter);

reviewsRouter.get('/filter',
    genericFilterMiddleware,
    reviewFilterMiddleware,
    (req, res, next) => {
        const sortField = req.query?.sortField;

        getPaginatedResults(Model, toPlainObject(req.entity_filters), req.generic_filters, sortField)
            .then((result) => {
                if (!result.totalPages)
                    throw ApiError.notFound("No reviews found matching the query");

                res.json(cleanResults(result));
            })
            .catch((err) => {
                // delegate to the next middleware (error handler)
                next(err)
            })
    }
)

module.exports = reviewsRouter
