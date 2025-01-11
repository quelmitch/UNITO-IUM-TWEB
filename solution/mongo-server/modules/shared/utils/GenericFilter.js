class GenericFilter {
    constructor({page, limit, sortOrder}) {
        this.page = page ? parseInt(page, 10) : 1;
        this.limit = limit ? parseInt(limit, 10) : 10;
        this.sortOrder = sortOrder?.toLowerCase() || 'asc';

        if (isNaN(this.page) || this.page < 1)
            this.page = 1;
        if (isNaN(this.limit) || this.limit < 1)
            this.limit = 10;
    }
}

module.exports = {GenericFilter}
