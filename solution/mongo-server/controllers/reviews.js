const Model = require('../models/reviews');

async function query(body) {
    try {
        const results = await Model.find(body).lean(); // Fetch data from MongoDB

        results.forEach((review) => {
            delete review._id
        });

        return results;
    } catch (error) {
        throw error;
    }
}

module.exports = {query};
