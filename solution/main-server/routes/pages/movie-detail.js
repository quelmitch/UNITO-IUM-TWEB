const express = require('express');
const axios = require("axios");
const iso3166 = require('iso-3166-1');
const { thisServer } = require('../../config/server')
const router = express.Router();

router.get('/:id', (req, res) => {
    const movie_id = req.params.id;
    if (!movie_id)
        res.status(400).send('Bad Request')

    axios.get(`${thisServer}/api/v1/movie/${movie_id}`)
        .then((response) => {
            response.data.releases.forEach((release) => {
                if (release.country) {
                    const countryCode = iso3166.whereCountry(release.country)?.alpha2;
                    if (countryCode) release.flagLink = `https://flagcdn.com/w40/${countryCode.toLowerCase()}.png`;
                    console.log(release.flagLink)
                }
            })


            res.render('pages/movie-detail', {
                title: response.data.title || 'Not Found',
                movie: response.data
            })
        })
        .catch((error) => {}) // TODO

})

module.exports = router;
