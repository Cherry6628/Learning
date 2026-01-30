import path from 'path';
import express from 'express';
import mysql2 from 'mysql2';

import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const{log}=console;




let app = express();
app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "public/views"));
app.use(express.static(path.join(__dirname, "public")));


app.get("/", (req, res) => {
    res.render("index", { title: "Glossarobot" });
});
app.get("/about", (req, res) => {
    res.render("about", { title: "About Us" });
});
app.use((req, res) => {
    res.status(404).send(`<h1>404 - Page not found</h1><p>Redirecting to home in 5 seconds...</p><script>setTimeout(()=>{window.location.href="/";}, 5000);</script>`);
});

const port = process.argv[2]||1234;
app.listen(port, (err,data)=>{
    if(err)log(err);
    if(data)log(data);
    let clearCode = "\x1b[1J\x1b[H\x1b[1J";
    log(clearCode+"127.0.0.1:"+port);
})





async function init(){
    let DBName = "DictionaryLookupDB";
}