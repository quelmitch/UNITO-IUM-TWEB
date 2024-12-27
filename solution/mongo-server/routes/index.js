var express = require('express');
var router = express.Router();

const controller = require("../controllers/reviews");

/**
 * @swagger
 * /query:
 *   get:
 *     summary: Query movie reviews
 *     parameters:
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
 *     responses:
 *       200:
 *         description: A list of movie reviews
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: object
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
