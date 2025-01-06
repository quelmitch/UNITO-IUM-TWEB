const express = require('express');
const router = express.Router();
const Model = require("./schema");

const { validateFilters, buildQuery, cleanResults } = require("./service");
const { getPaginatedResults } = require('@utils/paginationHelper');
const { ApiError } = require("@utils/errorHandler");


/**
 * @swagger
 * /review/filter:
 *   get:
 *     summary: Query movie reviews from the database
 *     description: Fetch movie reviews based on various query parameters. If no parameters are provided, returns all reviews.
 *     parameters:
 *       - name: movie_title
 *         in: query
 *         description: Title of the movie to filter reviews.
 *         required: false
 *         schema:
 *           type: string
 *           example: "Inception"
 *       - name: critic_name
 *         in: query
 *         description: Name of the critic to filter reviews by.
 *         required: false
 *         schema:
 *           type: string
 *           example: "Pete Hammond"
 *       - name: is_top_critic
 *         in: query
 *         description: Whether the critic is considered a top critic by Rotten Tomatoes.
 *         required: false
 *         schema:
 *           type: boolean
 *           example: false
 *       - name: publisher_name
 *         in: query
 *         description: Publisher or media outlet that released the review.
 *         required: false
 *         schema:
 *           type: string
 *           example: "Boxoffice Magazine"
 *       - name: type
 *         in: query
 *         description: The type of review.
 *         required: false
 *         schema:
 *           type: string
 *           enum: [Fresh, Rotten]
 *           example: "Fresh"
 *       - name: score
 *         in: query
 *         description: Review score, expressed in various formats (e.g., '5/5', 'A+', '10').
 *         required: false
 *         schema:
 *           type: string
 *           example: "5/5"
 *       - name: review_date
 *         in: query
 *         description: "The publication date of the review. Format: YYYY-MM-DD."
 *         required: false
 *         schema:
 *           type: string
 *           format: date
 *           example: "2010-07-05"
 *       - name: content
 *         in: query
 *         description: Full content or summary of the review.
 *         required: false
 *         schema:
 *           type: string
 *           example: "A wildly entertaining and dazzling mind-trip not to be missed. Kubrick would have been proud."
 *     responses:
 *       200:
 *         description: A list of movie reviews matching the query parameters.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: object
 *                 properties:
 *                   movie_title:
 *                     type: string
 *                     example: "Inception"
 *                   critic_name:
 *                     type: string
 *                     example: "Pete Hammond"
 *                   is_top_critic:
 *                     type: boolean
 *                     example: false
 *                   publisher_name:
 *                     type: string
 *                     example: "Boxoffice Magazine"
 *                   type:
 *                     type: string
 *                     enum: [Fresh, Rotten]
 *                     example: "Fresh"
 *                   score:
 *                     type: string
 *                     example: "5/5"
 *                   review_date:
 *                     type: string
 *                     format: date
 *                     example: "2010-07-05"
 *                   content:
 *                     type: string
 *                     example: "A wildly entertaining and dazzling mind-trip not to be missed. Kubrick would have been proud."
 *       400:
 *         description: Bad request – The request data is invalid (validation error).
 *       404:
 *         description: Not Found – No reviews found matching the query criteria.
 *       500:
 *         description: Internal Server Error – An unexpected error occurred on the server.
 */
router.get('/filter', (req, res, next) => {
    // Step 1: Validate filters
    validateFilters(req.query);

    // Step 2: Build query from filters
    const query = buildQuery(req.query);

    // Step 3: Execute query
    getPaginatedResults(Model, query)
        .then((result) => {
            if (!result.totalPages)
                throw ApiError.notFound("No reviews found matching the query"); // TODO: should return just the empty array or 404?

            res.json(cleanResults(result));
        })
        .catch((err) => {
            // delegate to the next middleware (error handler)
            next(err);
        })
})

module.exports = router
