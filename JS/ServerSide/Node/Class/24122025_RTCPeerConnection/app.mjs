import { Server } from "socket.io";
import http from 'http';
import express from 'express';

import { fileURLToPath } from "url";
import path from 'path';


const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const port = process.argv[2]||1234;
const{log}=console;

server.listen(port,(err,data)=>{
    if(err)log(err);
    if(data)log(data);
    log("127.0.0.1:"+port);
})

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "public/views"));
app.use(express.static(path.join(__dirname, "public")));


app.get("/", (req, res)=>{
    res.render("index.ejs");
})




io.on("connection",(socket)=>{
    console.log("User connected:",socket.id);

   socket.on("disconnect",()=>{
    console.log("User disconnected:",socket.id);
   });

   socket.on("offer",(offer)=>{
    socket.broadcast.emit("offer",{offer});
   });

   socket.on("answer",(answer)=>{
    socket.broadcast.emit("answer",{answer});
   });

   socket.on("ice-candidate",(candidate)=>{
    socket.broadcast.emit("ice-candidate",{candidate});
   });  

});
