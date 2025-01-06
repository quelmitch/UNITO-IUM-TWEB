async function getPaginatedResults(model, query, filters) {
    const page = filters?.page || 1;
    const limit = filters?.limit || 20;
    const sortOrder = filters?.sortOrder || 'asc';
    const sortField = filters?.sortField || '_id';

    const skip = (page - 1) * limit;
    const sort = {[sortField]: sortOrder === 'asc' ? 1 : -1};


    const results = await model.find(query)
        .skip(skip)
        .limit(limit)
        .sort(sort);

    const total = await model.countDocuments(query); // Total documents matching the query

    return {
        limit,
        page,
        totalPages: Math.ceil(total / limit),
        content: results,
    };
}

module.exports = {getPaginatedResults}
