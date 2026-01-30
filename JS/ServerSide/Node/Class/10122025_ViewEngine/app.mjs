import http from 'http';
import  fs from 'fs';
import path from 'path';
import express from 'express';

import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const{log}=console;

const app = express();
app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "/public/views"));
app.use(express.static(path.join(__dirname,"/public")));



app.get("/", (req, res)=>{
    res.render(path.join(__dirname,"public/views/index.ejs"));
});

app.get("/about", (req, res)=>{
    res.sendFile(path.join(__dirname,"public/views/about.html"))
});

const users = ["Sanjeevi", "Sanjee", "Sanj"]
app.get("/users", (req, res)=>{
    res.render("page", {users, title: "Dummy"});

})

app.get("/random", (req, res)=>{
    let min = parseInt(req.query.min)||0;
    let max = parseInt(req.query.max)||1;
    if (min>=max)max=min+1;
    res.setHeader("X-Powered-By", "");
    res.json({
        value:Math.floor(Math.random()*(max-min))+min
    })
})





const port = process.argv[2];
app.listen(port, (err,data)=>{
    if(err)console.log(err);
    if(data)console.log(data);
    console.log("Listening on port "+port+"\n127.0.0.1:"+port);
})
