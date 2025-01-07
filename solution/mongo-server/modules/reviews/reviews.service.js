const {ApiError} = require('@middleware/errorHandler');
const Model = require('./reviews.schema');

// TODO move into shared?
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
    cleanResults
}
