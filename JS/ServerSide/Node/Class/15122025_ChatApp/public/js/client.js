console.log("client.js loaded");
const clientSocket = io();

const msgInput = document.getElementById("msgInput");
const joinBtn = document.getElementById("joinBtn");
const leaveBtn = document.getElementById("leaveBtn");
const roomInput = document.getElementById("roomName");
const sendBtn = document.getElementById("sendBtn");
const sendRoomBtn = document.getElementById("sendBtnRoom");
const roomMsgInput = document.getElementById("roomMsgInput");
const messagesDiv = document.getElementById("messages");

//event listeners
sendBtn.addEventListener("click",()=>{
    const msg = msgInput.value;
    clientSocket.emit("chat-msg",msg); // emit event to server
    msgInput.value="";
})
joinBtn.addEventListener("click",()=>{
    const room = roomInput.value;
    clientSocket.emit("join-room",room);
})
leaveBtn.addEventListener("click",()=>{
    const room = roomInput.value;
    clientSocket.emit("leave-room",room);
})  
sendRoomBtn.addEventListener("click",()=>{
    const room = roomInput.value;
    const msg = roomMsgInput.value;
    clientSocket.emit("chat-msg-room",{room,msg}); // emit event to server
    roomMsgInput.value="";
})

//socket event handlers
clientSocket.on("chat-msg",(msg)=>{
    console.log("message received on client:",msg);
    const ptag = document.createElement("p");
    ptag.innerText=msg;
    messagesDiv.appendChild(ptag);
})
clientSocket.on("chat-msg-ack",(ackMsg)=>{
    console.log("acknowledgement from server:",ackMsg);
})
clientSocket.on("newUser",(msg)=>{
    console.log("new user joined:",msg);
    const ptag = document.createElement("p");
    ptag.innerText=msg;
    messagesDiv.appendChild(ptag);
})