var express = require('express');
var router = express.Router();

const controller = require("./reviews/controller");

/**
 * @swagger
 * /query:
 *   get:
 *     summary: Query movie reviews
 *     parameters:
 *       - name: rotten_tomatoes_link
 *         in: query
 *         description: Link to the Rotten Tomatoes page
 *         required: false
 *         schema:
 *           type: string
 *       - name: movie_title
 *         in: query
 *         description: Title of the movie
 *         required: false
 *         schema:
 *           type: string
 *       - name: critic_name
 *         in: query
 *         description: Name of the critic
 *         required: false
 *         schema:
 *           type: string
 *       - name: is_top_critic
 *         in: query
 *         description: Whether the critic is a top critic
 *         required: false
 *         schema:
 *           type: boolean
 *       - name: publisher_name
 *         in: query
 *         description: Name of the publisher
 *         required: false
 *         schema:
 *           type: string
 *       - name: type
 *         in: query
 *         description: Type of review (Fresh or Rotten)
 *         required: false
 *         schema:
 *           type: string
 *       - name: score
 *         in: query
 *         description: Review score
 *         required: false
 *         schema:
 *           type: string
 *       - name: review_date
 *         in: query
 *         description: Date the review was published
 *         required: false
 *         schema:
 *           type: string
 *           format: date
 *       - name: content
 *         in: query
 *         description: Full review content
 *         required: false
 *         schema:
 *           type: string
 *     responses:
 *       200:
 *         description: A list of movie reviews
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: object
 *                 properties:
 *                   rotten_tomatoes_link:
 *                     type: string
 *                   movie_title:
 *                     type: string
 *                   critic_name:
 *                     type: string
 *                   is_top_critic:
 *                     type: boolean
 *                   publisher_name:
 *                     type: string
 *                   type:
 *                     type: string
 *                   score:
 *                     type: string
 *                   review_date:
 *                     type: string
 *                     format: date
 *                   content:
 *                     type: string
 *       400:
 *         description: Invalid input data (validation error)
 *       404:
 *         description: No reviews found matching the query
 *       500:
 *         description: Internal Server Error
 */
router.get('/query', async function (req, res, next) {
    try {
        const results = await controller.query(req.query);
        res.json(results);
    } catch (err) {
        // delegate to the next middleware (error handler)
        next(err);
    }
});

module.exports = router;
