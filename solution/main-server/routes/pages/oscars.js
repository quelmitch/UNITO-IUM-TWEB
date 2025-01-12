const express = require('express');
const axios = require("axios");
const {thisServer} = require('@config/server')
const {fromObjectToUri} = require('@routes-utils/common-service')

const router = express.Router();

router.get('/', (req, res, next) => {
    axios.get(`${thisServer}/api/v1/oscar/ceremonies`)
        .then((ceremoniesResponse) => {
            // Get all ceremonies to know last ceremony
            const ceremonies = ceremoniesResponse.data.reverse();
            const lastCeremony = ceremonies[0].numberCeremony;

            const filters = req.query;
            filters.numberCeremony ??= lastCeremony; // last ceremony default value

            return Promise.all([
                axios.get(`${thisServer}/api/v1/oscar/filter?limit=500&${fromObjectToUri(filters)}`),
                Promise.resolve(ceremonies)
            ]);
        })
        .then(([oscarsResponse, ceremonies]) => {
            if (oscarsResponse.data.content.length < 1)
                return res.render('pages/error', {
                    error_status: 404,
                    error_name: 'Not Found',
                    error_message: 'No oscar found matching filters.',
                    error_redirect: '/oscars',
                });

            const lastCeremony = ceremonies[0].numberCeremony;

            res.render('pages/oscars', {
                title: 'Oscars',
                oscars: oscarsResponse.data.content[0],
                ceremonies: ceremonies,
                nextCeremony: oscarsResponse.data.content[0].numberCeremony + 1,
                previousCeremony: oscarsResponse.data.content[0].numberCeremony - 1,
                lastCeremony: lastCeremony
            });
        })
        .catch((error) => next(error))
});

module.exports = router;
