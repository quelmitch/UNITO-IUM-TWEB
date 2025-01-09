const express = require('express');
const axios = require('axios');
const { springbootServer } = require('@config/server')

const router = express.Router();

/**
 * @swagger
 * /genre/all:
 *   get:
 *     summary: Get all genres from the Spring Boot server
 *     description: Fetches a list of all genres from the server.
 *     responses:
 *       200:
 *         description: A list of genres
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: string
 *                 example:
 *       500:
 *         description: Internal Server Error
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 timestamp:
 *                   type: string
 *                   example: "2025-01-08T18:27:44.980+00:00"
 *                 status:
 *                   type: integer
 *                   example: 500
 *                 error:
 *                   type: string
 *                   example: "Internal Server Error"
 *                 path:
 *                   type: string
 *                   example: "/genre"
 */
router.get('/all', async (req, res) => {
    axios.get(`${springbootServer}/genre`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Failed to fetch data from Spring Boot server' })
        })
})

module.exports = router;
