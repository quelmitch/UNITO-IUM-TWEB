const express = require('express');
const router = express.Router();

const homeRouter = require('./home');
const moviesRouter = require('./movies');
const oscarsRouter = require('./oscars');
const reviewsRouter = require('./reviews');

router.use('/', homeRouter);
router.use('/movies', moviesRouter);
router.use('/oscars', oscarsRouter);
router.use('/reviews', reviewsRouter);

module.exports = router;
