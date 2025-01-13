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

const roomId = window.location.pathname;
// Connection to the Socket.IO server
const socket = io();

// Retrieve username from localStorage if it is set
let user = localStorage.getItem('username');
// Initial UI setup
if (!user)
    showUsernameInput();
else
    socket.emit('joinRoom', {room: roomId, username: user});

/* Helper functions */
function toggleChatPanel() {
    const isActive = chatPanel.classList.toggle('active');
    chatToggle.innerHTML = isActive ? '<span>&#x2715;</span>' : '<span>&#x1F4AC;</span>';

    if (!user)
        showUsernameInput();
}

function showUsernameInput() {
    usernameInput.focus();
    usernameInputContainer.style.display = 'block';
    chatMessages.style.display = 'none';
    chatInputContainer.style.display = 'none';
}

function hideUsernameInput() {
    usernameInputContainer.style.display = 'none';
    chatMessages.style.display = 'flex';
    chatInputContainer.style.display = 'flex';
}

function handleUsernameSubmit() {
    const enteredUsername = usernameInput.value.trim();

    if (enteredUsername) {
        localStorage.setItem('username', enteredUsername);
        user = enteredUsername;
        socket.emit('joinRoom', {room: roomId, username: user});
        hideUsernameInput();
    } else {
        usernameError.style.display = 'inline-block';
    }
}

function handleMessageReceived({username, message}) {
    if (!message) return;

    const userMessage = document.createElement('div');
    userMessage.className = getMessageClass(username);
    userMessage.textContent = username === 'System' ? message : `${username}: ${message}`;

    chatMessages.appendChild(userMessage);
    chatMessages.scrollTop = chatMessages.scrollHeight;
    chatInput.value = '';
}

function getMessageClass(username) {
    if (username === user)
        return 'message user';
    if (username === 'System')
        return 'message system';

    return 'message other';
}

function sendMessage() {
    const message = chatInput.value.trim();
    if (message) {
        socket.emit('message', {room: roomId, username: user, message});
        chatInput.value = '';
    }
}

/* Event listeners */
chatToggle.addEventListener('click', toggleChatPanel);
chatInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') chatSend.click();
});
chatSend.addEventListener('click', sendMessage);

usernameInput.addEventListener('keypress', (e) => {
    if (e.key === 'Enter') usernameSubmit.click();
});
usernameSubmit.addEventListener('click', handleUsernameSubmit);

socket.on('message', handleMessageReceived);
