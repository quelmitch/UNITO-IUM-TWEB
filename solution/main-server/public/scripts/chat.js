// Toggle chat panel
const chatToggle = document.getElementById('chatToggle');
const chatPanel = document.getElementById('chatPanel');
const chatMessages = document.getElementById('chatMessages');
const chatInput = document.getElementById('chatInput');
const chatSend = document.getElementById('chatSend');

const movieId = "film_1234";

// Connessione al server Socket.IO
const socket = io();

// UNCOMMENT TO ENTER USERNAME
//const user = prompt("Enter your username:") || 'User';
socket.emit('joinRoom', { room: movieId, username: user });

chatToggle.addEventListener('click', () => {
    const isActive = chatPanel.classList.toggle('active');
    chatToggle.innerHTML = isActive ? '<span>&#x2715;</span>' : '<span>&#x1F4AC;</span>';
});

socket.on('message', ({ username, message }) => {
    if (message) {
        const userMessage = document.createElement('div');
        if (username === user)
            userMessage.className = 'message user';
        else
            userMessage.className = 'message other';
        userMessage.textContent = username + ": " + message;
        chatMessages.appendChild(userMessage);
        chatMessages.scrollTop = chatMessages.scrollHeight; // Scroll to bottom
        chatInput.value = '';
    }
});

// Handle sending messages
chatSend.addEventListener('click', () => {
    const message = chatInput.value;
    if (message.trim()) {
        socket.emit('message', { room: movieId, username: user, message });
        chatInput.value = '';
    }
});

// Allow sending messages with Enter key
chatInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        chatSend.click();
    }
});