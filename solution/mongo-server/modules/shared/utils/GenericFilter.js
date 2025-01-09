const {ApiError} = require("../middleware/errorHandler");

class GenericFilter {
    constructor({page, limit, sortOrder}) {
        this.page = page ? parseInt(page, 10) : 1;
        this.limit = limit ? parseInt(limit, 10) : 10;
        this.sortOrder = sortOrder?.toLowerCase() || 'asc';

        if (isNaN(this.page) || this.page < 1)
            throw ApiError.badRequest('Page number must be a positive integer');
        if (isNaN(this.limit) || this.limit < 1)
            throw ApiError.badRequest('Limit must be a positive integer');
    }
}

module.exports = {GenericFilter}
