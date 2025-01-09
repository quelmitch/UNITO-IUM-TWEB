const express = require('express');
const axios = require("axios");
const { fromObjectToUri } = require('@routes-utils/common_service')
const { springbootServer } = require('@config/server')
const { groupByAndReduce } = require("./service")

const router = express.Router()

router.get('/filter', async (req, res) => {
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
