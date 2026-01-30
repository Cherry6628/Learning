console.log("client.js loaded");
const clientSocket = io();

const button = document.querySelector("button");
const roomName = document.querySelector("input[type=text]");
button.addEventListener('click',()=>{
    clientSocket.emit('join-room',roomName.value);
})