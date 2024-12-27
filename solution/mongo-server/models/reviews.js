const mongoose = require('mongoose');

// define the schema
const ReviewSchema = new mongoose.Schema({
        rotten_tomatoes_link: String,
        movie_title: String,
        critic_name: String,
        is_top_critic: Boolean,
        publisher_name: String,
        type: String,
        score: String,
        review_date: Date,
        content: String,
});

// export the Mongoose model
module.exports = mongoose.model('Review', ReviewSchema);
