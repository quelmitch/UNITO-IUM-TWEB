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
        this.movie_title = {$regex: new RegExp(movieTitle, 'i')}
        this.critic_name = {$regex: new RegExp(criticName, 'i')}
        this.is_top_critic = convertToBoolean(isTopCritic);
        this.publisher_name = {$regex: new RegExp(publisherName, 'i')}
        this.type = {$regex: new RegExp(type, 'i')}
        this.score = {$regex: new RegExp(score, 'i')}

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
