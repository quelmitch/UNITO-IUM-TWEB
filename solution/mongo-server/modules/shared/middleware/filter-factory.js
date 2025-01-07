const {GenericFilter} = require("@utils/GenericFilter");

function parseFilters(FilterClass) {
    return (req, res, next) => {
        try {
            if (FilterClass === GenericFilter)
                req.generic_filters = new FilterClass(req.query);
            else
                req.entity_filters = new FilterClass(req.query);
            next();
        } catch (error) {
            // TODO error handling
            res.status(400).json({error: error.message});
        }
    };
}

module.exports = {parseFilters}