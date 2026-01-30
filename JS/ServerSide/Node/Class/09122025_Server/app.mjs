import http from 'http';
import  fs from 'fs';
import path from 'path';

import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);



const server = http.createServer(handler);

const port = process.argv[2];

function readFile(file, handler=()=>{}){
    return fs.readFileSync(path.join(__dirname,file), handler);
}
function handler(req, res){
    res.setHeader("Content-Type","text/html")

    if (req.url=="/"){
        let file = readFile("public/views/index.html",(err,data)=>{
            res.statusCode = 500;
            res.end(err);
            console.log(err);

            return;
        });
        res.statusCode = 200;
        res.end(file);
    }
    else if (req.url=="/about"||req.url=="/about/"){
        let file = readFile("public/views/about.html",(err,data)=>{
            res.statusCode = 500;
            res.end(err);
            console.log(err);

            return;
        });
        res.statusCode = 200;
        res.end(file);
    }
    else {
        res.statusCode = 404;
        res.end("<a href=\"/\">Go back to home</a>")
    }

}

server.listen(port, (err,data)=>{
    if (err){
        console.log(err);
    }
    if (data){
        console.log(data);
    }
    console.log("Listening on port "+port);
})