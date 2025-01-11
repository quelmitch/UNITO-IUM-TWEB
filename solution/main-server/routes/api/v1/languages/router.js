const express = require('express');
const axios = require('axios');
const { springbootServer } = require('@config/server');

const router = express.Router();

/**
 * @swagger
 * /api/v1/language/all:
 *   get:
 *     tags:
 *       - Languages
 *     summary: Get all languages from the Spring Boot server
 *     description: Fetches a list of all languages from the server.
 *     responses:
 *       200:
 *         description: A list of languages
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: string
 *               example:
 *               - "Italian"
 *               - "English"
 *               - "Spanish"
 */
router.get('/all', async (req, res, next) => {
    axios.get(`${springbootServer}/language`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => next(error))
})

module.exports = router;
