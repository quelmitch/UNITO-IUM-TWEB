// Toggle chat panel
const chatToggle = document.getElementById('chatToggle');
const chatPanel = document.getElementById('chatPanel');
const chatMessages = document.getElementById('chatMessages');
const chatInput = document.getElementById('chatInput');
const chatSend = document.getElementById('chatSend');

const movieId = "film_1234";

// connection to the Socket.IO server
const socket = io();

// UNCOMMENT TO ENTER USERNAME
const user = prompt("Enter your username:") || 'User';
socket.emit('joinRoom', {room: movieId, username: user});

chatToggle.addEventListener('click', () => {
    const isActive = chatPanel.classList.toggle('active');
    chatToggle.innerHTML = isActive ? '<span>&#x2715;</span>' : '<span>&#x1F4AC;</span>';
});

socket.on('message', ({ username, message }) => {
    if (!message)
        return;

    const userMessage = document.createElement('div');

    userMessage.className = username === user
        ? 'message user'
        : username === 'System'
            ? 'message system'
            : 'message other';

    userMessage.textContent = username === 'System'
        ? message
        : `${username}: ${message}`;

    chatMessages.appendChild(userMessage);
    chatMessages.scrollTop = chatMessages.scrollHeight; // Scroll to bottom
    chatInput.value = '';
});

// Handle sending messages
chatSend.addEventListener('click', () => {
    const message = chatInput.value;
    if (message.trim()) {
        socket.emit('message', {room: movieId, username: user, message});
        chatInput.value = '';
    }
});

// Allow sending messages with Enter key
chatInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') {
        chatSend.click();
    }
});