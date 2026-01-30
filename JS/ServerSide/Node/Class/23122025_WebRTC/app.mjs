import express from "express";
import http from "http";
import { Server } from "socket.io";
import path from "path";
import { fileURLToPath } from "url";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
const server = http.createServer(app);
const io = new Server(server);

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "public/views"));

app.use(express.static(path.join(__dirname, "public")));
app.use(express.urlencoded({}))
app.use(express.urlencoded({extended:true}));
app.use(express.json());

const port = +process.argv[2]||1234;
const{log}=console;

server.listen(port, (err, data)=>{
    if(err)log(err);
    if(data)log(data);
    log("127.0.0.1:"+port);
})
app.get("/webrtc", (req, res)=>{
    log(req+"");
    res.render("index.ejs");
})

io.on('connection', (socket)=>{
    socket.on('')
})