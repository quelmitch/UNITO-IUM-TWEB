const mongoose = require('mongoose');

const database = 'mongodb://localhost:27017/IUM-TWEB';
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


// TODO add config env