const express = require('express');
const router = express.Router();

const reviewRouter = require("./reviews/router");

router.use('/review', reviewRouter)

module.exports = router;
