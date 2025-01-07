class GenericFilter {
    constructor({page, limit, sortOrder}) {
        this.page = page ? parseInt(page, 10) : 1;
        this.limit = limit ? parseInt(limit, 10) : 10;
        this.sortOrder = sortOrder || 'asc';

        // TODO check error handling
        if (isNaN(this.page) || this.page < 1) throw new Error('Page must be a positive integer'); // TODO < 0 or < 1 ?
        if (isNaN(this.limit) || this.limit < 1) throw new Error('Limit must be a positive integer');
    }
}

module.exports = {GenericFilter}