const {ApiError} = require('../../utils/errorHandler');
const Model = require('./schema');

async function query(filters) {
    try {
        // use Mongoose schema for casting and validation
        const tempModel = new Model(filters);
        const validationError = tempModel.validateSync();
        if (validationError) {
            const errorMessage = Object.values(validationError.errors)
                .map(err => `${err.path}: ${err.message}`)
                .join(', ');

            throw ApiError.badRequest(`Data validation failed: ${errorMessage}`);
        }

        const query = {};
        Object.keys(filters).forEach((key) => {
            const value = tempModel[key];

            if (value) {
                // case-insensitive
                if (typeof value === "string")
                    query[key] = {$regex: new RegExp(value, 'i')};
                else
                    query[key] = value;
            }
        });

        let results;
        try {
            results = await Model.find(query).lean();
        } catch (err) {
            throw ApiError.internal("Database query failed: " + err.message);
        }

        if (!results.length)
            throw ApiError.notFound("No reviews found matching the query");

        // clean the response by removing _id from each result
        results.forEach((review) => {
            delete review._id;
        });

        return results;
    } catch (error) {
        // re-throw the error to be handled by the error handler
        throw error;
    }
}

module.exports = {query};
