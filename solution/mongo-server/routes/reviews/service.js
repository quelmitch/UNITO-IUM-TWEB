const {ApiError} = require('@utils/errorHandler');
const {getPaginatedResults} = require('@utils/paginationHelper');
const Model = require('./schema');

function validateFilters(filters) {
    // List of extra filters allowed (pagination purpose)
    const extraFilters = ['page', 'limit', 'totalPages', 'sortOrder', 'sortField'];
    // Get all filter keys
    const filterKeys = Object.keys(filters);
    // Get schema fields
    const schemaFields = Object.keys(Model.schema.paths);

    // Determine if any filter is not part of schema fields or allowed extra filters
    const invalidFilters = filterKeys.filter(key => !schemaFields.includes(key) && !extraFilters.includes(key));

    if (invalidFilters.length > 0)
        throw ApiError.badRequest(`Invalid filters provided: ${invalidFilters.join(', ')}`);

    // Validate that page, limit, and totalPages are integers if they are present
    extraFilters.forEach(key => {
        if (filters[key] !== undefined && filters[key] !== null) {
            // Try to cast the value to an integer
            const intValue = parseInt(filters[key], 10);

            // Check if the value is a valid integer
            if (isNaN(intValue) || intValue.toString() !== filters[key].toString())
                throw ApiError.badRequest(`${key} must be a valid integer`);

            // If valid, update the filter to the integer value
            filters[key] = intValue;
        }
    });

    // Proceed with model validation
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
                query[key] = {$regex: new RegExp(value, 'i')};
            else
                query[key] = value;
        }
    });

    return query;
}

function cleanResults(results) {
    results.content = results.content.map(doc => {
        const {_id, review_date, ...rest} = doc.toObject();

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
}
