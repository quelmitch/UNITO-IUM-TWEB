const express = require('express');
const axios = require("axios");
const { thisServer } = require('@config/server')
const { fromObjectToUri } = require('@routes-utils/common_service')

const router = express.Router();

router.get('/', (req, res) => {
    const filters = req.query;

    axios.get(`${thisServer}/api/v1/oscar/filter?${fromObjectToUri(filters)}`)
        .then((response) => {
            console.log(response.data.content)
            res.render('pages/oscars', {
                title: 'Oscars',
                oscars: response.data.content
            })
        })
        .catch((error) => {
            console.log(error)
        }) // TODO
});

module.exports = router;
