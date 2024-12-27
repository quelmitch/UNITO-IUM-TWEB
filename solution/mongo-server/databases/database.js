const mongoose = require('mongoose')

const database = 'mongodb://localhost:27017/IUM-TWEB';
mongoose.Promise = global.Promise;

connection = mongoose.connect(database, {
    checkServerIdentity: false
})
    .then(() => {
        console.log('connected to mongodb');
    })
    .catch((err) => {
        console.log('connection error: ', +JSON.stringify(err));
    })
