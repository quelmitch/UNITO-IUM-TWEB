const express = require('express');
const axios = require("axios");
const { fromObjectToUri } = require('@routes-utils/common_service')
const { springbootServer } = require('@config/server')
const { groupByAndReduce } = require("./service")

const router = express.Router()

/**
 * @swagger
 * /api/v1/oscar/filter:
 *   get:
 *     tags:
 *       - Oscars
 *     summary: Fetch Oscars by filters
 *     description: TODO
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
 *         name: numberCeremonyGT
 *         schema:
 *           type: integer
 *           example: 75
 *         description: Filter by the number of the ceremony greater than the specified value.
 *       - in: query
 *         name: numberCeremonyLT
 *         schema:
 *           type: integer
 *           example: 100
 *         description: Filter by the number of the ceremony less than the specified value.
 *       - in: query
 *         name: yearCeremonyGT
 *         schema:
 *           type: integer
 *           example: 2000
 *         description: Filter by the year of the ceremony greater than the specified value.
 *       - in: query
 *         name: yearCeremonyLT
 *         schema:
 *           type: integer
 *           example: 2020
 *         description: Filter by the year of the ceremony less than the specified value.
 *       - in: query
 *         name: yearMovie
 *         schema:
 *           type: integer
 *           example: 2015
 *         description: Filter by the year of the movie.
 *       - in: query
 *         name: category
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["ACTOR IN A LEADING ROLE"]
 *         description: List of categories for the Oscar nominations.
 *       - in: query
 *         name: nomineeName
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Rami Malek"]
 *         description: List of nominee names.
 *       - in: query
 *         name: nomineeMovie
 *         schema:
 *           type: array
 *           items:
 *             type: string
 *           example: ["Bohemian Rhapsody"]
 *         description: List of movies nominated for the Oscar.
 *       - in: query
 *         name: isWinner
 *         schema:
 *           type: boolean
 *           example: true
 *         description: Indicates whether the nominee was a winner.
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
 *                   example: 545
 *                 page:
 *                   type: integer
 *                   example: 0
 *                 content:
 *                   type: array
 *                   items:
 *                     type: object
 *                     properties:
 *                       numberCeremony:
 *                         type: integer
 *                         example: 1
 *                       yearCeremony:
 *                         type: integer
 *                         example: 1928
 *                       yearMovie:
 *                         type: integer
 *                         example: 1927
 *                       category:
 *                         type: string
 *                         example: "ACTOR"
 *                       nomineeName:
 *                         type: string
 *                         example: "Richard Barthelmess"
 *                       nomineeMovie:
 *                         type: string
 *                         example: "The Noose"
 *                       isWinner:
 *                         type: boolean
 *                         example: false
 */
router.get('/filter', async (req, res, next) => {
    const numberCeremony = req.query?.numberCeremony ? parseInt(req.query.numberCeremony, 10) : null
    const yearCeremony = req.query?.yearCeremony ? parseInt(req.query.yearCeremony, 10) : null
    if (numberCeremony){
        req.query.numberCeremonyGT = numberCeremony-1
        req.query.numberCeremonyLT = numberCeremony+1
        delete req.query.numberCeremony
    }
    if (yearCeremony){
        req.query.yearCeremonyGT = yearCeremony-1
        req.query.yearCeremonyLT = yearCeremony+1
        delete req.query.yearCeremony
    }
    const filters = fromObjectToUri(req.query)

    axios.get(`${springbootServer}/oscar/filter?${filters}`)
        .then((response) => {
            const data = groupByAndReduce(response.data)
            res.json(data)
        })
        .catch((error) => {
            // TODO
            console.log(error)
            res.status(500).json({ error: 'Error communicating with the Spring Boot server' })
        })
})

/**
 * @swagger
 * /api/v1/oscar/ceremonies:
 *   get:
 *     tags:
 *       - Oscars
 *     summary: Get all Oscar ceremonies from the Spring Boot server
 *     description: Fetches a list of all Oscar ceremony objects from the server.
 *     responses:
 *       200:
 *         description: OK
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: object
 *                 properties:
 *                   numberCeremony:
 *                     type: integer
 *                     example: 1
 *                   yearCeremony:
 *                     type: integer
 *                     example: 1928
 *             example:
 *               - numberCeremony: 1
 *                 yearCeremony: 1928
 *               - numberCeremony: 2
 *                 yearCeremony: 1929
 */
router.get('/ceremonies', async (req, res) => {
    axios.get(`${springbootServer}/oscar/ceremonies`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => {
            // TODO
            console.log(error)
            res.status(500).json({ error: 'Error communicating with the Spring Boot server' })
        })
})

module.exports = router;
