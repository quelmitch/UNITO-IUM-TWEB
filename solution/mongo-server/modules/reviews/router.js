const express = require('express');
const reviewsRouter = express.Router();

// Reviews import
const Model = require("./schema");
const {cleanResults} = require("./service");
const {ReviewFilter} = require("./filter");

// Utils import
const {getPaginatedResults, toPlainObject} = require('@utils/routers-utils');
const {GenericFilter} = require("@utils/generic-filter");
const {parseFilters} = require("@middleware/filter-factory");

const genericFilterMiddleware = parseFilters(GenericFilter);
const reviewFilterMiddleware = parseFilters(ReviewFilter);

/**
 * @swagger
 * /review/filter:
 *   get:
 *     tags:
 *       - Reviews
 *     summary: Fetch Reviews by filters
 *     description: Fetches a list of Reviews from the database based on the provided filter criteria. Returns all reviews if no parameters are specified.
 *     parameters:
 *       - name: page
 *         in: query
 *         description: The page number of the results to retrieve.
 *         required: false
 *         schema:
 *           type: integer
 *           example: 1
 *       - name: limit
 *         in: query
 *         description: The number of results to return per page.
 *         required: false
 *         schema:
 *           type: integer
 *           example: 10
 *       - name: sortOrder
 *         in: query
 *         description: The order in which to sort the results.
 *         required: false
 *         schema:
 *           type: string
 *           enum: [asc, desc]
 *           example: "asc"
 *       - name: sortField
 *         in: query
 *         description: The field by which to sort the results.
 *         required: false
 *         schema:
 *           type: string
 *           enum: [movie_title, critic_name, is_top_critic, publisher_name, type, score, review_date]
 *           example: ""
 *       - name: movieTitle
 *         in: query
 *         description: Filter reviews by the title of the movie.
 *         required: false
 *         schema:
 *           type: string
 *           example: "Inception"
 *       - name: criticName
 *         in: query
 *         description: Filter reviews by the critic's name.
 *         required: false
 *         schema:
 *           type: string
 *           example: "Pete Hammond"
 *       - name: isTopCritic
 *         in: query
 *         description: Specify if the critic is recognized as a top critic by Rotten Tomatoes.
 *         required: false
 *         schema:
 *           type: boolean
 *           example: false
 *       - name: publisherName
 *         in: query
 *         description: Filter reviews by the publisher or media outlet.
 *         required: false
 *         schema:
 *           type: string
 *           example: "Boxoffice Magazine"
 *       - name: type
 *         in: query
 *         description: Filter by the review type.
 *         required: false
 *         schema:
 *           type: string
 *           enum: [Fresh, Rotten]
 *           example: "Fresh"
 *       - name: score
 *         in: query
 *         description: Filter by review score, which can be in various formats (e.g., '5/5', 'A+', '10').
 *         required: false
 *         schema:
 *           type: string
 *           example: "5/5"
 *       - name: reviewDateGT
 *         in: query
 *         description: "Minimum review date (format: YYYY-MM-DD)."
 *         required: false
 *         schema:
 *           type: string
 *           format: date
 *           example: "2010-07-04"
 *       - name: reviewDateLT
 *         in: query
 *         description: "Maximum review date (format: YYYY-MM-DD)."
 *         required: false
 *         schema:
 *           type: string
 *           format: date
 *           example: "2010-07-06"
 *     responses:
 *       200:
 *         description: A list of movie reviews that match the query parameters.
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 limit:
 *                   type: integer
 *                   example: 10
 *                 page:
 *                   type: integer
 *                   example: 1
 *                 totalPages:
 *                   type: integer
 *                   example: 1
 *                 content:
 *                   type: array
 *                   items:
 *                     type: object
 *                     properties:
 *                       rotten_tomatoes_link:
 *                         type: string
 *                         example: "m/inception"
 *                       movie_title:
 *                         type: string
 *                         example: "Inception"
 *                       critic_name:
 *                         type: string
 *                         example: "Pete Hammond"
 *                       is_top_critic:
 *                         type: boolean
 *                         example: false
 *                       publisher_name:
 *                         type: string
 *                         example: "Boxoffice Magazine"
 *                       type:
 *                         type: string
 *                         enum: [Fresh, Rotten]
 *                         example: "Fresh"
 *                       score:
 *                         type: string
 *                         example: "5/5"
 *                       review_date:
 *                         type: string
 *                         format: date
 *                         example: "2010-07-05"
 *                       content:
 *                         type: string
 *                         example: "A wildly entertaining and dazzling mind-trip not to be missed. Kubrick would have been proud."
 *       404:
 *         description: Not Found – No reviews match the query criteria.
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 status:
 *                   type: integer
 *                   example: 404
 *                 message:
 *                   type: string
 *                   example: "Not Found"
 *       500:
 *         description: Internal Server Error – An unexpected error occurred.
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 status:
 *                   type: integer
 *                   example: 500
 *                 message:
 *                   type: string
 *                   example: "Internal Server Error"
 */
reviewsRouter.get('/filter',
    genericFilterMiddleware,
    reviewFilterMiddleware,
    (req, res, next) => {
        const sortField = req.query?.sortField;

        getPaginatedResults(Model, toPlainObject(req.entity_filters), req.generic_filters, sortField)
            .then((result) => {
                res.json(cleanResults(result));
            })
            .catch((err) => next(err))
    }
)

module.exports = reviewsRouter
