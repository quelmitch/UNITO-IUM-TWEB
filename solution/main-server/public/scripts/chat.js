// Toggle chat panel
const chatToggle = document.getElementById('chatToggle');
const chatPanel = document.getElementById('chatPanel');
const chatMessages = document.getElementById('chatMessages');
const chatInput = document.getElementById('chatInput');
const chatSend = document.getElementById('chatSend');
const chatInputContainer = document.getElementById('chatInputContainer');

const usernameInputContainer = document.getElementById('usernameInputContainer');
const usernameInput = document.getElementById('usernameInput');
const usernameSubmit = document.getElementById('usernameSubmit');

const roomId = window.location.href

// connection to the Socket.IO server
const socket = io();

// Retrieve username from localStorage
let user = localStorage.getItem('username');
if (!user) {
    usernameInputContainer.style.display = 'block';
    chatMessages.style.display = 'none';
    chatInputContainer.style.display = 'none';
}

socket.emit('joinRoom', {room: roomId, username: user});

chatToggle.addEventListener('click', () => {
    const isActive = chatPanel.classList.toggle('active');
    chatToggle.innerHTML = isActive ? '<span>&#x2715;</span>' : '<span>&#x1F4AC;</span>';

    if (!user) {
        usernameInputContainer.style.display = 'block';
        chatMessages.style.display = 'none';
        chatInputContainer.style.display = 'none';
    }
});

usernameSubmit.addEventListener('click', () => {
    const enteredUsername = usernameInput.value.trim();
    if (enteredUsername) {
        localStorage.setItem('username', enteredUsername);

        user = enteredUsername;

        socket.emit('joinRoom', {room: roomId, username: user});

        usernameInputContainer.style.display = 'none';
        chatMessages.style.display = 'block';
        chatInputContainer.style.display = 'block';
    } else {
        alert('Please enter a valid username');
    }
});

socket.on('message', ({username, message}) => {
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
    chatMessages.scrollTop = chatMessages.scrollHeight; // scroll to the last message
    chatInput.value = '';
});

// Handle sending messages
chatSend.addEventListener('click', () => {
    const message = chatInput.value;
    if (message.trim()) {
        socket.emit('message', {room: roomId, username: user, message});
        chatInput.value = '';
    }
});

// Allow sending messages with Enter key
chatInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter')
        chatSend.click();
});