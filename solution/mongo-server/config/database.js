const mongoose = require('mongoose');

const databaseName = "IUM-TWEB"
const database = `mongodb://localhost:27017/${databaseName}`;
mongoose.Promise = global.Promise;

mongoose.connect(database, {
    checkServerIdentity: false
})
    .then(() => {
        console.log('connected to mongodb');
    })
    .catch((err) => {
        console.error('connection error:', err);
    });