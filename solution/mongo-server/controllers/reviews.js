const Model = require('../models/reviews');

async function query(filters) {
    try {
        const query = {};

        Object.keys(filters).forEach((key) => {
            const value = filters[key];
            console.log(typeof value)
            if (value) {
                // case-insensitive
                if (typeof value === 'string')
                    query[key] = {$regex: new RegExp(value, 'i')};
                else
                    // non-string types
                    query[key] = value;
            }
        });

        const results = await Model.find(filters).lean();
        results.forEach((review) => {
            delete review._id;
        });

        return results;
    } catch (error) {
        throw error;
    }
}

module.exports = { query };
