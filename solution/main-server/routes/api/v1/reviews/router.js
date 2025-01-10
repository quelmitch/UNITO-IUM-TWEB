const express = require('express');
const axios = require('axios');
const { fromObjectToUri } = require('@routes-utils/common_service')
const { mongodbServer} = require('@config/server')

const router = express.Router();

/**
 * @swagger
 * /api/v1/review/filter:
 *   get:
 *     tags:
 *       - Reviews
 *     summary: Fetch reviews by filters
 *     description: TODO
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
 *       400:
 *         description: Bad Request – Invalid or malformed request.
 *       404:
 *         description: Not Found – No reviews match the query criteria.
 *       500:
 *         description: Internal Server Error – An unexpected error occurred.
 */
router.get('/filter', async (req, res) => {
    const filters = fromObjectToUri(req.query)

    axios.get(`${mongodbServer}/review/filter?${filters}`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Failed to fetch data from MongoDB server' })
        })
})

module.exports = router;
