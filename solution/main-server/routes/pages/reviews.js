const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.render('pages/reviews', { title: 'Reviews', reviews: null });
});

module.exports = router;
