import http from 'http';
import  fs from 'fs';
import path from 'path';
import express from 'express';

import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const{log}=console;

const app = express();
app.get("/", (req, res)=>{
    res.sendFile(path.join(__dirname,"public/views/index.html"))
});
app.get("/about", (req, res)=>{
    res.sendFile(path.join(__dirname,"public/views/about.html"))
});


const port = process.argv[2];
app.listen(port, (err,data)=>{
    if(err)console.log(err);
    if(data)console.log(data);
    console.log("Listening on port "+port);
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