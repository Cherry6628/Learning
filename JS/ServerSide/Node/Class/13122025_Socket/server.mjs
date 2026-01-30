import express from 'express';
import {Server} from 'socket.io';
import path from 'path';
import http from 'http';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const{log}=console;

const app = express();
const httpServer = http.createServer(app);
const io = new Server(httpServer);


app.use(express.static(path.join(__dirname,"/public")));
app.get("/", (req, res)=>{
    res.sendFile(path.join(__dirname,"index.html"))
})
httpServer.listen(process.argv[2]||1234,(err,data)=>{
    if (err)log(err);
    if (data)log(data);
    log("\n")
})
io.on('connection', (socket)=>{
    console.log("Hello");
    console.log("User Connected: "+socket.id)
    socket.on('chat-message',(msg)=>{
        log("Message from User "+socket.id+": "+msg);
        io.emit('chat-from-server', msg);
    })
})
