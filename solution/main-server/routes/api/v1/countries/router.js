const express = require('express');
const axios = require('axios');
const {springbootServer} = require('@config/server')

const router = express.Router();

/**
 * @swagger
 * /api/v1/country/all:
 *   get:
 *     tags:
 *       - Countries
 *     summary: Get all countries from the Spring Boot server
 *     description: Fetches a list of all countries from the server.
 *     responses:
 *       200:
 *         description: A list of countries
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: string
 *               example:
 *                 - "Afghanistan"
 *                 - "Albania"
 *                 - "Algeria"
 */
router.get('/all', async (req, res, next) => {
    axios.get(`${springbootServer}/country`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => next(error))
})

module.exports = router;
