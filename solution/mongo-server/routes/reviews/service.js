const { ApiError } = require('@utils/errorHandler');
const { getPaginatedResults } = require('@utils/paginationHelper');
const Model = require('./schema');

function validateFilters(filters) {
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
    results.content = results.content.map(doc => {
        const { _id, review_date, ...rest } = doc.toObject();

        return {
            ...rest,
            review_date: review_date instanceof Date && !isNaN(review_date)
                ? review_date.toISOString().split('T')[0]
                : review_date,
        };
    });
    return results;
}


module.exports = {
    buildQuery,
    validateFilters,
    cleanResults
};
