const express = require('express');
const axios = require('axios');
const { fromObjectToUri } = require('../../../utils/common_service')
const { mongodbServer} = require('../../../../config/server')

const router = express.Router();

router.get('/filter', async (req, res) => {
    const filters = fromObjectToUri(req.query)

    axios.get(`${mongodbServer}/review/filter?${filters}`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Failed to fetch data from MongoDB server' })
        })
})

module.exports = router;
