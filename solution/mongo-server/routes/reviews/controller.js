const {ApiError} = require('../../utils/errorHandler');
const Model = require('./schema');

function validateFilters(filters) {
    if (Object.keys(filters).length === 0)
        throw ApiError.badRequest("No filters provided");

    const tempModel = new Model(filters);
    const validationError = tempModel.validateSync();

    if (validationError) {
        const errorMessage = Object.values(validationError.errors)
            .map(err => `${err.path}: ${err.message}`)
            .join(', ');

        throw ApiError.badRequest(`Data validation failed: ${errorMessage}`);
    }
}

function buildQuery(filters) {
    const tempModel = new Model(filters);
    const query = {};

    Object.keys(filters).forEach((key) => {
        const value = tempModel[key];

        if (value !== undefined && value !== null) {
            // case-insensitive
            if (typeof value === "string")
                query[key] = { $regex: new RegExp(value, 'i') };
            else
                query[key] = value;
        }
    });

    return query;
}

function cleanResults(results) {
    return results.map(review => {
        const tempReview = { ...review };

        delete tempReview._id;
        if (tempReview.review_date instanceof Date && !isNaN(tempReview.review_date))
            tempReview.review_date = tempReview.review_date.toISOString().split('T')[0];

        return tempReview;
    });
}

async function query(filters) {
    try {
        // Step 1: Validate filters
        validateFilters(filters);

        // Step 2: Build query from filters
        const query = buildQuery(filters);

        // Step 3: Execute query
        let results;
        try {
            results = await Model.find(query).lean();
        } catch (err) {
            throw ApiError.internal("Database query failed: " + err.message);
        }

        if (!results.length)
            throw ApiError.notFound("No reviews found matching the query");

        // Step 4: Clean and return the response
        return cleanResults(results);
    } catch (error) {
        // re-throw the error to be handled by the error handler
        throw error;
    }
}

module.exports = {query};
