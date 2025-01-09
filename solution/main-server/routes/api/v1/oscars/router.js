const express = require('express');
const axios = require("axios");
const { fromObjectToUri } = require('@routes-utils/common_service')
const { springbootServer } = require('@config/server')
const { groupByCeremonyAndCategory } = require("./service")

const router = express.Router()

router.get('/filter', async (req, res) => {
    const filters = fromObjectToUri(req.query)

    axios.get(`${springbootServer}/oscar/filter?${filters}`)
        .then((response) => {
            const data = groupByCeremonyAndCategory(response.data)
            res.json(data)
        })
        .catch((error) => {
            // TODO
            console.log(error)
            res.status(500).json({ error: 'Error communicating with the Spring Boot server' })
        })
})

module.exports = router;
