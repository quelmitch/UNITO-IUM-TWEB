class ReviewFilter {
    constructor({
                    movieTitle,
                    criticName,
                    isTopCritic,
                    publisherName,
                    type,
                    reviewDateGT,
                    reviewDateLT,
                    score
                }) {
        this.$text = movieTitle ? { $search: movieTitle } : undefined;
        this.critic_name = criticName ? {$regex: new RegExp(criticName, 'i')} : undefined;
        this.is_top_critic = isTopCritic ? convertToBoolean(isTopCritic) : undefined;
        this.publisher_name = publisherName ? {$regex: new RegExp(publisherName, 'i')} : undefined;
        this.type = type ? {$regex: new RegExp(type, 'i')} : undefined;
        this.score = score ? {$regex: new RegExp(score, 'i')} : undefined;

        if (reviewDateGT || reviewDateLT) {
            this.review_date = {};
            if (reviewDateGT)
                this.review_date.$gt = new Date(reviewDateGT);
            if (reviewDateLT)
                this.review_date.$lt = new Date(reviewDateLT);
        } else
            this.review_date = undefined;
    }
}

function convertToBoolean(value) {
    if (typeof value === 'string')
        return value.toLowerCase() === 'true';

    return !!value;
}

module.exports = {ReviewFilter}
