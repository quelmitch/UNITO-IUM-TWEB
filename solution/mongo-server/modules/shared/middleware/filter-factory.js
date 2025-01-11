const {GenericFilter} = require("@utils/GenericFilter");

function parseFilters(FilterClass) {
    return (req, res, next) => {
        if (FilterClass === GenericFilter)
            req.generic_filters = new FilterClass(req.query);
        else
            req.entity_filters = new FilterClass(req.query);
        next();
    };
}

module.exports = {parseFilters}
