import express from "express";
import http from "http";
import { Server } from "socket.io";
import {v5, NIL,MAX} from 'uuid';

import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const port = process.argv[2] || 1234;
const{log}=console;

app.use(express.static(path.join(__dirname, "public")));
app.set('viewengine','ejs');


// function getInCookie(){
//     return "in="+v5('In', NIL)+";max-age="+604800;
// }
// function hasCookie(cookie){

// }hghghghghghghghghgh
app.get("/", (req, res) => {
    // res.setHeader("Set-Cookie", getInCookie());
    // res.removeHeader("X-Powered-By");

    res.render(path.join(__dirname, "public/home.ejs"));
});

server.listen(port, (err, data) => {
    if(err)return log(err);
    if(data)log(data);
    log("\x1bc"+"127.0.0.1:"+port);

});
let admins = {};
let socksList = new Set();
io.on('connection',(socket)=>{
    log("User Connected\t: "+socket.id);
    socket.on('join-room',(room)=>{
        if(socket.data.room||socksList.has(socket.id))return socket.emit("redirect", "/room.ejs");
        log(room);
        // socket.data.roomName=data;
        socket.join(room);
        socket.data.room = room;
        socksList.add(socket.id);
        if(!admins[room])admins[room] = {sockets:[], admin:undefined};
        if(admins[room].sockets.length==0)admins[room].admin=socket;
        if(!admins[room].sockets.includes(socket))admins[room].sockets.push(socket);
        log(admins);
        // res.redirect()
        return socket.emit("redirect", "/room.ejs");
    })

    socket.on('disconnect',()=>{ // leave-room //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        log(admins);
        log("User disconnected: "+socket.id);
        if(!socket.data.room)return;
        let socks = admins[socket.data.room].sockets;
        for(let i=0;i<socks.length;i++){
            let soc = socks[i];
            if(!soc.connected){
                admins[socket.data.room].sockets.splice(i, 1);
                admins[socket.data.room].admin=socks[0];
                socksList.delete(socket.id);
                log("c "+soc.id);
                log(admins);
                break;
            }
        }
    })
});
