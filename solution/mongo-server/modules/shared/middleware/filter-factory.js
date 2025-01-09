const {GenericFilter} = require("@utils/GenericFilter");
const {ApiError} = require("./errorHandler");

function parseFilters(FilterClass) {
    return (req, res, next) => {
        try {
            if (FilterClass === GenericFilter)
                req.generic_filters = new FilterClass(req.query);
            else
                req.entity_filters = new FilterClass(req.query);
            next();
        } catch (error) {
            throw ApiError.badRequest('Unable to parse filters');
        }
    };
}

module.exports = {parseFilters}
