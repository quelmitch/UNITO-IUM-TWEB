const Model = require('../models/reviews');

async function query(filters) {
    try {
        const query = {};

        Object.keys(filters).forEach((key) => {
            const value = filters[key];
            if (value) {
                if (typeof value === 'string')
                    // case-insensitive
                    query[key] = { $regex: new RegExp(value, 'i') };
                else
                    // non-string types
                    query[key] = value;
            }
        });

        const results = await Model.find(query).lean();
        results.forEach((review) => {
            delete review._id;
        });

        return results;
    } catch (error) {
        throw error;
    }
}

module.exports = { query };
