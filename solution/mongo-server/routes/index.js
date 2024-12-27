var express = require('express');
var router = express.Router();

const controller = require("../controllers/reviews");

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
 *                     description: Link to the Rotten Tomatoes page
 *                   movie_title:
 *                     type: string
 *                     description: Title of the movie
 *                   critic_name:
 *                     type: string
 *                     description: Name of the critic
 *                   is_top_critic:
 *                     type: boolean
 *                     description: Whether the critic is a top critic
 *                   publisher_name:
 *                     type: string
 *                     description: Name of the publisher
 *                   type:
 *                     type: string
 *                     description: Type of review (e.g., text, video)
 *                   score:
 *                     type: string
 *                     description: Review score
 *                   review_date:
 *                     type: string
 *                     format: date
 *                     description: Date the review was published
 *                   content:
 *                     type: string
 *                     description: Full review content
 */
router.get('/query', async function (req, res, next) {
    try {
        const results = await controller.query(req.query);
        res.json(results);
    } catch (err) {
        res.status(500).json({ error: err });
    }
});

module.exports = router;

// TODO: check for is_top_critic on the query (treated like a string instead of a boolean)