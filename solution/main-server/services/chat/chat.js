module.exports = (io) => {
    const users = {};
    const roomHistory = {};

    io.on('connection', (socket) => {
        // console.log('A user has logged in!');

        socket.on('joinRoom', ({room, username}) => {
            socket.join(room);

            if (!users[room])
                users[room] = [];
            users[room].push({id: socket.id, username});

            // console.log(`${username} joined room: ${room}`);

            if (roomHistory[room])
                roomHistory[room].forEach((message) => {
                    socket.emit('message', message)
                });

            io.to(room).emit('message', {
                username: 'System',
                message: `${username} joined room`
            });
        });

        socket.on('message', ({room, message, username}) => {
            // console.log(`User ${username} says "${message}" in the room: ${room}`);

            if (!roomHistory[room])
                roomHistory[room] = [];
            roomHistory[room].push({username, message});

            if (roomHistory[room].length > 20)
                roomHistory[room].shift()

            io.to(room).emit('message', {username, message});
        });

        socket.on('disconnect', () => {
            console.log('A user has logged out');
        });
    });
};
