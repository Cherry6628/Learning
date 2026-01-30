const{log}=console;

log("Hello");

const clientSocket = io();


const click = document.getElementById("click");
const msg = document.getElementById("msgInp");
const chat = document.getElementById("chat");

function showMessage(msg){
    if(!msg)return;
    let d = document.createElement("div");
    d.innerText = msg;
    d.style.border = "1px solid black";
    chat.appendChild(d);
}

click.addEventListener('click',(e)=>{
    const data = msg.value;
    if(!data)return;
    clientSocket.emit("chat-message",data);
    msg.value="";
})
clientSocket.on('chat-from-server', (data)=>{
    showMessage(data);
})