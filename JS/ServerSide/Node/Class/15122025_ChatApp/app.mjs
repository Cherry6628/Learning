import express from 'express';
import http from 'http';
import path from 'path';
import {Server} from 'socket.io';
import { fileURLToPath } from 'url';
import { instrument } from '@socket.io/admin-ui';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const app = express();
const server = http.createServer(app);
const io = new Server(server, {
  cors: {
    origin: ["https://admin.socket.io"],
    credentials: true
  }
});
instrument(io, {
  auth: false,
  mode: "development",
});

app.use(express.static('public'));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname,'public/index.html'));
});
// app.get('/admin', (req, res) => {
//   res.sendFile(path.join(__dirname,'public/admin.html'));
// });
io.on("connection",(socket)=>{
    console.log('a user connected',socket.id);

    socket.broadcast.emit("newUser","A new user has joined the chat");

    socket.on("disconnect",()=>{
        console.log('user disconnected',socket.id);
    });

    socket.on("chat-msg",(msg)=>{
        console.log("message received on server:",msg);
        //broadcast to all clients including sender
        io.emit("chat-msg",msg);
        socket.emit("chat-msg-ack", "Message received loud and clear!");
    })

    socket.on("join-room",(room)=>{
        socket.join(room);
        socket.emit("chat-msg-ack", `Joined room: ${room}`);
        console.log(`Socket ${socket.id} joined room ${room}`);
    })
    socket.on("leave-room",(room)=>{
        socket.leave(room);
        socket.emit("chat-msg-ack", `Left room: ${room}`);
        console.log(`Socket ${socket.id} left room ${room}`);
    })
    socket.on("chat-msg-room",({room,msg})=>{
        console.log(`message for room ${room} received on server:`,msg);
        //broadcast to all clients in the room including sender
        io.to(room).emit("chat-msg",`[Room: ${room}] ${msg}`);
        socket.emit("chat-msg-ack", `Message sent to room: ${room}`);
    })
    
})


// io.of("/admin").on("connection",(socket)=>{
//     console.log('a user connected',socket.id);

//     socket.broadcast.emit("newUser","A new user has joined the chat");

//     socket.on("disconnect",()=>{
//         console.log('user disconnected',socket.id);
//     });

//     socket.on("chat-msg",(msg)=>{
//         console.log("message received on server:",msg);
//         //broadcast to all clients including sender
//         io.emit("chat-msg",msg);
//         socket.emit("chat-msg-ack", "Message received loud and clear!");
//     })

//     socket.on("join-room",(room)=>{
//         socket.join(room);
//         socket.emit("chat-msg-ack", `Joined room: ${room}`);
//         console.log(`Socket ${socket.id} joined room ${room}`);
//     })
//     socket.on("leave-room",(room)=>{
//         socket.leave(room);
//         socket.emit("chat-msg-ack", `Left room: ${room}`);
//         console.log(`Socket ${socket.id} left room ${room}`);
//     })
//     socket.on("chat-msg-room",({room,msg})=>{
//         console.log(`message for room ${room} received on server:`,msg);
//         //broadcast to all clients in the room including sender
//         io.to(room).emit("chat-msg",`[Room: ${room}] ${msg}`);
//         socket.emit("chat-msg-ack", `Message sent to room: ${room}`);
//     })
    
// })

server.listen(8000,()=>{
    console.log("Server is running on port 8000");
});











// const express = require('express');
// const http = require('http');
// const path = require('path');
// const {Server} = require('socket.io');

// const app = express();
// const server = http.createServer(app);
// const io = new Server(server);

// io.on('connection',(socket)=>{
//     console.log('a user connected',socket.id);
//     socket.on("chat-msg",(msg)=>{
//         console.log("message received on server:",msg);
//         //broadcast to all clients including sender
//         io.emit("chat-msg",msg);
//     })
// })

// app.use(express.static(path.join(__dirname,'public')));

// app.get("/",(req,res)=>{
//     res.sendFile(path.join(__dirname,'/index.html'));
// });

// server.listen(3000,()=>{
//     console.log("Server is running on port 3000");
// });