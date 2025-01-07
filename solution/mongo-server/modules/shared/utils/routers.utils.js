async function getPaginatedResults(model, query, filters) {
    const sortField = filters?.sortField || '_id'; // TODO remove

    const skip = (filters.page - 1) * filters.limit;
    const sort = {[sortField]: filters.sortOrder === 'asc' ? 1 : -1};

    const results = await model.find(query)
        .skip(skip)
        .limit(filters.limit)
        .sort(sort);

    const total = await model.countDocuments(query);

    return {
        limit: filters.limit,
        page: filters.page,
        totalPages: Math.ceil(total / filters.limit),
        content: results,
    };
}

function toPlainObject(classInstance) {
    return Object.fromEntries(
        Object.entries(classInstance).filter(([_, value]) => value !== undefined)
    );
}

module.exports = {
    getPaginatedResults,
    toPlainObject
}
