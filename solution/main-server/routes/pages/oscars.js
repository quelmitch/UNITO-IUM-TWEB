const express = require('express');
const axios = require("axios");
const { thisServer } = require('@config/server')
const { fromObjectToUri } = require('@routes-utils/common_service')

const router = express.Router();

router.get('/', (req, res) => {
    const filters = req.query;

    Promise.all([
        axios.get(`${thisServer}/api/v1/oscar/filter?limit=500&${fromObjectToUri(filters)}`),
        axios.get(`${thisServer}/api/v1/oscar/ceremonies`),
    ])
        .then(([oscarsResponse, ceremoniesResponse]) => {
            res.render('pages/oscars', {
                title: 'Oscars',
                oscars: oscarsResponse.data.content,
                ceremonies: ceremoniesResponse.data,
                nextCeremony: oscarsResponse.data.content.numberCeremony + 1,
                previousCeremony: oscarsResponse.data.content.numberCeremony - 1,
                lastCeremony: ceremoniesResponse.data[ceremoniesResponse.data.length - 1].numberCeremony,
            })
        })
        .catch((error) => {
            console.log(error)
        }) // TODO
});

// TODO REMOVE
/*axios.get(`http://localhost:3000/oscars/partial?limit=500&numberCeremony=${selectedCeremony}`)
    //.then(response => JSON.stringify(response.data))
    .then(response => {
        categoriesContainer.innerHTML = response.data;
    })
    .catch(error => {
        console.error(error);
    });
router.get('/partial', (req, res) => {
    const filters = req.query;

    axios.get(`${thisServer}/api/v1/oscar/filter?limit=500&ceremonyNumber=2&${fromObjectToUri(filters)}`)
        .then(response => {
            const oscarsData = response.data.content;
            console.log(oscarsData);
            res.render('partials/oscars/oscar-categories', { oscars: oscarsData }); // Assuming your partial is oscars-details.handlebars
        })
        .catch(error => {
            console.error('Error fetching ceremony data:', error);
            res.status(500).send('Error retrieving ceremony details'); // Handle errors appropriately
        });
});
*/
module.exports = router;
