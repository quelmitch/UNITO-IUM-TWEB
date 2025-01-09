module.exports = (io) => {
    const users = {};

    io.on('connection', (socket) => {
        console.log('Un utente si è connesso');

        // Gestione delle stanze
        socket.on('joinRoom', ({ room, username }) => {
            socket.join(room);

            // Add user to the tracking object
            if (!users[room]) users[room] = [];
            users[room].push({ id: socket.id, username });

            console.log(`${username} joined room: ${room}`);

            // Notify everyone in the room about the new user
            io.to(room).emit('message', {
                username: 'System',
                message: `${username} has joined the room.`,
            });
        });

        // Gestione dei messaggi
        socket.on('message', ({ room, message, username }) => {
            console.log(`Utente ${username} dice "${message}" nella stanza: ${room}`);
            io.to(room).emit('message', { username, message });
        });

        // Disconnessione
        socket.on('disconnect', () => {
            console.log('Un utente si è disconnesso');
        });
    });
};