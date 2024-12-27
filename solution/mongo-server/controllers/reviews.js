const Model = require('../models/reviews');

async function query(body) {
    try {
        const results = await Model.find(body).lean();
        results.forEach((review) => {
            delete review._id; // Remove `_id` for each review
        });
        return results;
    } catch (error) {
        throw error;
    }
}

module.exports = { query };
