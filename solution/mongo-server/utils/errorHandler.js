class ApiError extends Error {
    constructor(status, message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    static badRequest(message = "Bad Request") {
        return new ApiError(400, message);
    }

    static notFound(message = "Not Found") {
        return new ApiError(404, message);
    }

    static internal(message = "Internal Server Error") {
        return new ApiError(500, message);
    }
}

const errorHandler = (err, req, res, next) => {
    if (err instanceof ApiError)
        return res.status(err.status).json({status: err.status, error: err.message});

    return res.status(500).json({error: "Internal Server Error"});
};

module.exports = {ApiError, errorHandler};
