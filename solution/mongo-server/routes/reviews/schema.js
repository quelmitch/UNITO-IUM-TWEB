const mongoose = require('mongoose');

const ReviewSchema = new mongoose.Schema({
    rotten_tomatoes_link: {type: String},
    movie_title: {type: String},
    critic_name: {type: String},
    is_top_critic: {type: Boolean},
    publisher_name: {type: String},
    type: {
        type: String,
        enum: ['Fresh', 'Rotten']
    },
    score: {type: String,},
    review_date: {type: Date, default: Date.now},
    content: {type: String}
});

module.exports = mongoose.model('Review', ReviewSchema);
