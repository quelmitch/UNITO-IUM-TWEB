const express = require('express')
const axios = require('axios')
const { fromObjectToUri } = require('@routes-utils/common_service')
const { springbootServer } = require('@config/server')

const router = express.Router()

router.get('/filter', async (req, res) => {
    const filters = fromObjectToUri(req.query)

    axios.get(`${springbootServer}/movie/filter?${filters}`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Error communicating with the Spring Boot server' })
        })
})

router.get('/:id', async (req, res) => {
    const movie_id = req.params.id
    if (!movie_id)
        res.status(400).send('Bad Request')

    axios.get(`${springbootServer}/movie/${movie_id}`)
        .then((response) => {
            res.json(response.data)
        })
        .catch((error) => {
            // TODO
            res.status(500).json({ error: 'Errore nella comunicazione con il server Spring Boot' })
        })
})

module.exports = router
