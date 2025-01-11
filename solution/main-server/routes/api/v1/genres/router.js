const express = require('express');
const axios = require('axios');
const { springbootServer } = require('@config/server');

const router = express.Router();

/**
 * @swagger
 * /api/v1/genre/all:
 *   get:
 *     tags:
 *       - Genres
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
 *               example:
 *                 - "Italian"
 *                 - "Japanese"
 *                 - "Korean"
 */
router.get('/all', async (req, res, next) => {
    axios.get(`${springbootServer}/genre`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => next(error))
})

module.exports = router;
