const {GenericFilter} = require("@utils/generic-filter");

/* Middleware to dynamically parse a group of filters into filters objects, */
function parseFilters(FilterClass) {
    return (req, res, next) => {
        // Generic filtering
        if (FilterClass === GenericFilter)
            req.generic_filters = new FilterClass(req.query);
        // Entity filtering
        else
            req.entity_filters = new FilterClass(req.query);
        next();
    };
}

module.exports = {parseFilters}
