const express = require('express');
const axios = require('axios');
const { springbootServer } = require('@config/server')

const router = express.Router();

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
