class ReviewFilter {
    constructor({
                    movieTitle,
                    criticName,
                    isTopCritic,
                    publisherName,
                    type,
                    reviewDateGT,
                    reviewDateLT,
                    score,
                    sortField
    }) {
        this.movie_title = movieTitle
        this.critic_name = criticName
        this.is_top_critic = isTopCritic // TODO parse Boolean
        this.publisher_name = publisherName
        this.type = type
        this.score = score
        this.sort_field = sortField

        if (reviewDateGT || reviewDateLT) { // Check if at least one date is provided
            this.review_date = {};
            if (reviewDateGT)
                this.review_date.$gt = new Date(reviewDateGT);
            if (reviewDateLT)
                this.review_date.$lt = new Date(reviewDateLT);
        } else
            this.review_date = undefined; // Set to undefined if both are missing

        // if (this.review_date.$gt || !this.review_date.$lt) throw new Error('Incorrect Date Format'); // TODO is needed?
    }
}

module.exports = {ReviewFilter}