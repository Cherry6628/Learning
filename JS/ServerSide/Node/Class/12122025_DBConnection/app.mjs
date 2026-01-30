import fs from 'fs';
import path from 'path';
import express from 'express';

import { fileURLToPath } from 'url';
import db from "./db.mjs"


const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const{log}=console;


let app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "/public/views"));
app.use(express.static(path.join(__dirname,"/public")));

app.get("/", (req, res)=>{
    res.render(path.join(__dirname,"public/views/index.ejs"));

})

app.get("/login", (req, res)=>{
    res.render(path.join(__dirname,"public/views/login.ejs"));

})
app.get("/signup", (req, res)=>{
    res.render(path.join(__dirname,"public/views/signup.ejs"));

})

app.post("/signup", (req, res)=>{
    // log(req.headers.cookie);

    const{uname,mail,pwd,cpwd}=req.body;
    if (pwd!=cpwd || !pwd || !uname || !mail){
        return res.status(400).json({ success: false, message: "Invalid input data or passwords do not match." });;
    }

    db.query("select * from users where email=?",[mail],(err,data)=>{
        if(err){
            log("Error: "+err);
            return res.status(500).json({ success: false, message: "A server error occurred during user lookup." });
        }
        log("Data: "+data);
        if(data && data.length>0){
            log("Account already exists");
            return res.status(409).json({ success: false, message: "Account with this email already exists. Please log in.", redirect:true });
        }
        else {

            db.query("insert into users(name,email,pwd) values (?,?,?)",[uname,mail,pwd],(e,d)=>{
                if (e){
                    log("Error: "+e);
                    res.status(500).send("Some error occurred");
                    return;
                }
                log("\tData: "+data);
                log(JSON.stringify(data.keys()));
                if (data && data.affectedRows > 0) {
                    log(`User ${uname} registered successfully.`);
                    return res.status(201).json({success: true, message: "Registration successful. Redirecting to login.", redirect:true});
                } else {
                    return res.status(500).json({ success: false, message: "Registration failed due to unknown database issue." });
                }
            })
            
    
        }
    });

});

app.post("/login", (req,res)=>{
    const { mail, pwd } = req.body;
    
    if (!mail || !pwd) {
        return res.status(400).json({ success: false, message: "Email and password are required." });
    }

    db.query("SELECT * FROM users WHERE email = ?", [mail], (err, users) => {
        if (err) {
            log("Login Lookup Error: " + err);
            return res.status(500).json({ success: false, message: "A server error occurred." });
        }

        if (users.length === 0) {
            log("Login failed: User not found for email " + mail);
            return res.status(401).json({ success: false, message: "Invalid email or password." });
        }

        const user = users[0];
        if (user.pwd === pwd) {
            log(`User ${user.name} logged in successfully.`);
            return res.status(200).json({ 
                success: true, 
                message: `Logged in successfully as ${user.name}!`,
                user: { uname: user.name, mail: user.email }
            });
        } else {
            log("Login failed: Incorrect password for email " + mail);
            return res.status(401).json({ success: false, message: "Invalid email or password." });
        }
    });
});




const port = process.argv[2]||1234;

app.listen(port, (err,data)=>{
    if(err)console.log(err);
    if(data)console.log(data);
    console.log("\x1b[2J\x1b[HListening on port "+port+"\n127.0.0.1:"+port);

})
