const mongoose = require('mongoose');

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

// Add a virtual getter for `date`
// ReviewSchema.virtual('date').get(function () {
//         return this.review_date;
// });

// Ensure virtuals are included when converting documents to JSON
// ReviewSchema.set('toJSON', { getters: true, virtuals: true });

module.exports = mongoose.model('Review', ReviewSchema);
