module.exports = (io) => {
    const rooms = {};
    const roomHistory = {};

    io.on('connection', (socket) => {
        // console.log('A user has logged in!');

        socket.on('joinRoom', ({room, username}) => {
            socket.join(room);

            if (!rooms[room])
                rooms[room] = [];
            rooms[room].push({id: socket.id, username});

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
            for (const room in rooms) {
                const userIndex = rooms[room].findIndex(user => user.id === socket.id);
                if (userIndex !== -1) {
                    const username = rooms[room][userIndex].username;
                    rooms[room].splice(userIndex, 1);

                    io.to(room).emit('message', {
                        username: 'System',
                        message: `${username} has left the room`
                    });

                    break;
                }
            }

            // console.log('A user has logged out');
        });
    });
};
