const express = require('express');
const axios = require('axios');
const { fromObjectToUri } = require('@routes-utils/common_service');
const { springbootServer } = require('@config/server');

const router = express.Router()

/**
 * @swagger
 * /api/v1/movie/filter:
 *   get:
 *     tags:
 *       - Movies
 *     summary: Fetch movies by filters
 *     description: Fetches a list of movies that match the given filters, supporting pagination, case-insensitive search, and sorting by various fields.
 *     parameters:
 *       - in: query
 *         name: page
 *         schema:
 *           type: integer
 *           default: 0
 *           example: 0
 *         description: The page number to retrieve.
 *       - in: query
 *         name: limit
 *         schema:
 *           type: integer
 *           default: 20
 *           example: 10
 *         description: The maximum number of items to return per page.
 *       - in: query
 *         name: responseType
 *         schema:
 *           type: string
 *           enum: [BASIC, FULL]
 *           default: BASIC
 *           example: BASIC
 *         description: The type of response to return.
 *       - in: query
 *         name: sort
 *         schema:
 *           type: string
 *           enum: [ASC, DESC]
 *           default: ASC
 *           example: ASC
 *         description: The sort order.
 *       - in: query
 *         name: sortBy
 *         schema:
 *           type: string
 *           default: id
 *           example: id
 *         description: Field by which to sort the results.
 *       - in: query
 *         name: title
 *         schema:
 *           type: string
 *           example: Barbie
 *         description: Filter movies with a title like the specified value.
 *       - in: query
 *         name: runtimeGT
 *         schema:
 *           type: integer
 *           example: 100
 *         description: Filter movies with a runtime greater than the specified value.
 *       - in: query
 *         name: runtimeLT
 *         schema:
 *           type: integer
 *           example: 120
 *         description: Filter movies with a runtime less than the specified value.
 *       - in: query
 *         name: releaseYearGT
 *         schema:
 *           type: integer
 *           example: 2010
 *         description: Filter movies released after the specified year.
 *       - in: query
 *         name: releaseYearLT
 *         schema:
 *           type: integer
 *           example: 2024
 *         description: Filter movies released before the specified year.
 *       - in: query
 *         name: ratingGT
 *         schema:
 *           type: number
 *           format: double
 *           example: 3.1
 *         description: Filter movies with a rating greater than the specified value.
 *       - in: query
 *         name: ratingLT
 *         schema:
 *           type: number
 *           format: double
 *           example: 4.5
 *         description: Filter movies with a rating less than the specified value.
 *       - in: query
 *         name: actor
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Margot Robbie"]
 *         description: List of actors in the movie.
 *       - in: query
 *         name: crew
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Greta Gerwig"]
 *         description: List of crew members in the movie.
 *       - in: query
 *         name: genre
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Comedy", "Adventure"]
 *         description: List of genres the movie belongs to.
 *       - in: query
 *         name: productionCountry
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["USA"]
 *         description: List of countries where the movie was produced.
 *       - in: query
 *         name: language
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["English"]
 *         description: List of languages in which the movie was released.
 *       - in: query
 *         name: releaseCountry
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["USA"]
 *         description: List of countries where the movie was released.
 *       - in: query
 *         name: studio
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Mattel"]
 *         description: List of studios that produced the movie.
 *       - in: query
 *         name: character
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Barbie"]
 *         description: List of characters played by the actors in the movie.
 *       - in: query
 *         name: audienceRating
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["PG"]
 *         description: List of audience ratings the movie has received.
 *     responses:
 *       200:
 *         description: OK
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 limit:
 *                   type: integer
 *                   example: 10
 *                 totalPages:
 *                   type: integer
 *                   example: 47080
 *                 page:
 *                   type: integer
 *                   example: 0
 *                 content:
 *                   type: array
 *                   items:
 *                     type: object
 *                     properties:
 *                       id:
 *                         type: integer
 *                         example: 1000001
 *                       title:
 *                         type: string
 *                         example: "Barbie"
 *                       releaseYear:
 *                         type: integer
 *                         example: 2023
 *                       tagline:
 *                         type: string
 *                         example: "She's everything. He's just Ken."
 *                       description:
 *                         type: string
 *                         example: "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans."
 *                       runtime:
 *                         type: integer
 *                         example: 114
 *                       rating:
 *                         type: number
 *                         format: double
 *                         example: 3.86
 *                       posterLink:
 *                         type: string
 *                         example: "https://a.ltrbxd.com/resized/film-poster/2/7/7/0/6/4/277064-barbie-0-230-0-345-crop.jpg?v=1b83dc7a71"
 */
router.get('/filter', async (req, res, next) => {
    const filters = fromObjectToUri(req.query)

    axios.get(`${springbootServer}/movie/filter?${filters}`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => next(error))
})

/**
 * @swagger
 * /api/v1/movie/{id}:
 *   get:
 *     tags:
 *       - Movies
 *     summary: Fetch movie by ID
 *     description: Fetches a movie from the Spring Boot server with the given ID.
 *     parameters:
 *       - in: path
 *         name: id
 *         schema:
 *           type: integer
 *         required: true
 *         description: ID of the movie to retrieve
 *         example: 1000001
 *     responses:
 *       200:
 *         description: OK
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 id:
 *                   type: integer
 *                   example: 1000001
 *                 title:
 *                   type: string
 *                   example: "Barbie"
 *                 releaseYear:
 *                   type: integer
 *                   example: 2023
 *                 tagline:
 *                   type: string
 *                   example: "She's everything. He's just Ken."
 *                 description:
 *                   type: string
 *                   example: "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans."
 *                 runtime:
 *                   type: integer
 *                   example: 114
 *                 rating:
 *                   type: number
 *                   format: float
 *                   example: 3.86
 *                 posterLink:
 *                   type: string
 *                   example: "https://a.ltrbxd.com/resized/film-poster/2/7/7/0/6/4/277064-barbie-0-230-0-345-crop.jpg?v=1b83dc7a71"
 */
router.get('/:id', async (req, res, next) => {
    const movie_id = req.params.id
    if (!movie_id)
        res.status(400).send('Bad Request')

    axios.get(`${springbootServer}/movie/${movie_id}`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => next(error))
})

module.exports = router
