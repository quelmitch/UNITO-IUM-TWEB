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
const usernameError = document.getElementById('usernameError');

const roomId = window.location.href

// connection to the Socket.IO server
const socket = io();

// Retrieve username from localStorage if it set
let user = localStorage.getItem('username');
if (!user) {
    usernameInputContainer.style.display = 'block';
    chatMessages.style.display = 'none';
    chatInputContainer.style.display = 'none';
} else {
    socket.emit('joinRoom', { room: roomId, username: user });
}

chatToggle.addEventListener('click', () => {
    const isActive = chatPanel.classList.toggle('active');
    chatToggle.innerHTML = isActive ? '<span>&#x2715;</span>' : '<span>&#x1F4AC;</span>';

    if (!user) {
        usernameInput.focus()
        usernameInputContainer.style.display = 'block';
        chatMessages.style.display = 'none';
        chatInputContainer.style.display = 'none';
    }
});

usernameSubmit.addEventListener('click', () => {
    const enteredUsername = usernameInput.value.trim();
    // Check if the entered username is not empty
    if (enteredUsername) {
        // Store the username in localStorage for persistence
        localStorage.setItem('username', enteredUsername);

        user = enteredUsername;

        socket.emit('joinRoom', { room: roomId, username: user });

        usernameError.style.display = 'inline-block';
        usernameInputContainer.style.display = 'none';
        chatMessages.style.display = 'flex';
        chatInputContainer.style.display = 'flex';
    } else {
        usernameError.style.display = 'inline-block';
    }
});

// Handle incoming messages
socket.on('message', ({ username, message }) => {
    if (!message) return;

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
    chatMessages.scrollTop = chatMessages.scrollHeight;
    chatInput.value = '';
});

// Handle sending messages
chatSend.addEventListener('click', () => {
    const message = chatInput.value;
    if (message.trim()) {
        socket.emit('message', { room: roomId, username: user, message });
        chatInput.value = '';
    }
});

// Allow sending messages with Enter key
chatInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter')
        chatSend.click();
});
// Allow confirms username with Enter key
usernameInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter')
        usernameSubmit.click();
});
