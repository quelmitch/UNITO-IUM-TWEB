var express = require('express');
var router = express.Router();

const controller = require("../controllers/reviews")

/**
 * @swagger
 * /query:
 *   post:
 *     summary: Fetch movie reviews from MongoDB
 *     tags: [Reviews]
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               movie_title:
 *                 type: string
 *                 description: Title of the movie to filter reviews.
 *             example:
 *               movie_title: "Inception"
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
 *                     type: number
 *                   review_date:
 *                     type: string
 *                     format: date
 *                   content:
 *                     type: string
 *       500:
 *         description: Internal server error
 */
router.post('/query', async function(req, res, next) {
  try {
    const results = await controller.query(req.body);
    res.json(results);
  } catch (err) {
    res.status(500).json({ error: err });
  }
});


module.exports = router;
