module.exports = (io) => {
    const HISTORY_LIMIT = 20
    const rooms = {}; // Store active rooms and users
    const roomHistory = {}; // Store message history for each room

    io.on('connection', (socket) => {
        socket.on('joinRoom', ({room, username}) => {
            socket.join(room);

            // Add the user to the room's list
            if (!rooms[room])
                rooms[room] = [];
            rooms[room].push({id: socket.id, username});

            // Sent previous messages to the user
            if (roomHistory[room])
                roomHistory[room].forEach((message) => {
                    socket.emit('message', message)
                });

            // Notify the room that a user has joined
            io.to(room).emit('message', {
                username: 'System',
                message: `${username} joined room`
            });
        });

        socket.on('message', ({room, message, username}) => {
            // Save the new message to the room's history
            if (!roomHistory[room])
                roomHistory[room] = [];
            roomHistory[room].push({username, message});

            // Limit room history to the last HISTORY_LIMIT messages
            if (roomHistory[room].length > HISTORY_LIMIT)
                roomHistory[room].shift()

            // Broadcast the message to everyone in the room
            io.to(room).emit('message', {username, message});
        });

        socket.on('disconnect', () => {
            // Find and remove the user from the room
            for (const room in rooms) {
                const userIndex = rooms[room].findIndex(user => user.id === socket.id);
                if (userIndex !== -1) {
                    const username = rooms[room][userIndex].username;
                    rooms[room].splice(userIndex, 1);

                    // Notify the room that a user has left
                    io.to(room).emit('message', {
                        username: 'System',
                        message: `${username} has left the room`
                    });

                    // Exit after finding and removing the user
                    break;
                }
            }
        });
    });
};
