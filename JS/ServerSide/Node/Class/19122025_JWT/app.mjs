import express from "express";
import path from "path";
import { fileURLToPath } from "url";
import session from 'express-session';
import jwt from 'jsonwebtoken';
import dotconfig from 'dotenv';
import cookieParser from "cookie-parser";

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const{log}=console;
const app = express();

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "public/views"));
app.use(express.static(path.join(__dirname, "public")));
function secret(n){let r = "";for(let i=0;i<n;i++)r+="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"[Math.floor(Math.random()*62)];return r;}
app.use(session({
    name: "SessionID",
    secret: secret(1000),
    resave: false, //Don’t save session if nothing changed
    saveUninitialized: false, //Don’t create session for visitors who didn’t login
    cookie: {
        maxAge: 1000 * 3600,
        httpOnly: true,
        secure: false
    }
}))

app.use(express.urlencoded({extended:true}));
app.use(express.json());
app.use(cookieParser());


const verifyJWT = (req, res, next)=>{
    const token=req.cookies.tok;
    if(!token) return res.redirect("/login");
    try{
        req.user=jwt.verify(token,superSecret);
        return next();
    } catch(e){
        return res.redirect("/login");
    }
}

const verifyInvalidJWT = (req, res, next)=>{
    const token=req.cookies.tok;
    if(!token)return next();
    try{
        req.user=jwt.verify(token,superSecret);
        return res.redirect("/");
    } catch(e){
        return next();
    }
}
const port = (+process.argv[2])||1234;
const superSecret = "HelloWorld@12121234";

const map = new Map();
map.set("asdf","asdf");
app.get("/login", verifyInvalidJWT, (req, res)=>{
    res.render("login");
})

app.get("/signup", verifyInvalidJWT, (req, res)=>{
    res.render("signup.ejs");
})

app.post("/signup",verifyInvalidJWT,(req, res)=>{
    // const uname = ""+req.query.uname;
    // const pwd = ""+req.query.pwd;

    const uname=req.body.uname,pwd=req.body.pwd;
    if(map.has(uname)){
        res.send("<p>Account already exists.<p><br><a href=\"/login\">Login</a>");
        return;
    }
    map.set(uname,pwd);
    return res.redirect("/login");
})
app.post("/login", verifyInvalidJWT,(req, res)=>{
    // const uname = ""+req.query.uname;
    // const pwd = ""+req.query.pwd;

    const uname=req.body.uname,pwd=req.body.pwd;
    if(map.has(uname)&&map.get(uname)==pwd){
        const token = jwt.sign(
            {uname,pwd},
            superSecret,
            {expiresIn:30}
        )
        log(token);
        res.cookie("tok", token,{httpOnly:true});
        return res.redirect("/");
    }

    res.send("<p>Invalid Credentials.<p><br><a href=\"/signup\">Signup</a>")
})


app.get("/", verifyJWT, (req, res)=>{
    return res.render("index.ejs");
})



app.post("/logout",(req, res)=>{
    req.session?.destroy(()=>{
        res.redirect("/login");
    })
})
app.get("/logout",(req, res)=>{
    req.session?.destroy(()=>{
        res.redirect("/login");
    })
})



app.listen(port, (err, data)=>{
    if(err)log(err);
    if(data)log(data);
    log("127.0.0.1:"+port);
})
