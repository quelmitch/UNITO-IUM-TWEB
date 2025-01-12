
/* Make the results more readable */
function cleanResults(results) {
    results.content = results.content.map(doc => {
        const {_id, review_date, ...rest} = doc.toObject();

        return {
            ...rest,
            // From timestamp with timezone to only date
            review_date: review_date instanceof Date && !isNaN(review_date)
                ? review_date.toISOString().split('T')[0]
                : review_date,
        };
    });
    return results;
}

module.exports = {cleanResults}
