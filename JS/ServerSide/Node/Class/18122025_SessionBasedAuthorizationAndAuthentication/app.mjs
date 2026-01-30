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

const views=(name)=>path.join(__dirname,"public/views", name);
const isLoggedIn = (req, res, next) => {
    // log(req.session);
    // log(req.session.isAuthorized);
    if (req.session?.isAuthorized) {
        return next();
    }
    return res.redirect("/login");
};
const isNotLoggedIn = (req, res, next)=>{
    if (req.session?.isAuthorized){
        return res.redirect("/");
    }
    return next();
}

const port = (+process.argv[2])||1234;
const superSecret = "HelloWorld@12121234";

const map = new Map();
map.set("","");
app.get("/login", isNotLoggedIn, (req, res)=>{
    
    res.render(views("login"));
})

app.get("/signup", isNotLoggedIn, (req, res)=>{
    res.render(views("signup.ejs"));
})

app.post("/signup",isNotLoggedIn,(req, res)=>{
    // const uname = ""+req.query.uname;
    // const pwd = ""+req.query.pwd;

    const uname=req.body.uname,pwd=req.body.pwd;
    if(map.has(uname)){
        res.send("<p>Account already exists.<p><br><a href=\"/login\">Login</a>");
        return;
    }
    map.set(uname,pwd);
    res.redirect("/login");
})
app.post("/login", isNotLoggedIn,(req, res)=>{
    
    // const uname = ""+req.query.uname;
    // const pwd = ""+req.query.pwd;
    const uname=req.body.uname,pwd=req.body.pwd;
    if(map.has(uname)&&map.get(uname)==pwd){
        // log("Session: "+req.session);
        req.session.isAuthorized = true;
        req.session.uname=uname;
        const token = jwt.sign(
            {uname,pwd},
            superSecret,
            {expiresIn:3000}
        )
        log(token);
        res.cookie("tok", token,{httpOnly:true});

        // log(req.session.isAuthorized);
        // log(req.session.uname);
        res.redirect("/");
        return;
    }

    res.send("<p>Invalid Credentials.<p><br><a href=\"/signup\">Signup</a>")
})


app.get("/", isLoggedIn, (req, res)=>{
    // if(req?.session?.isAuthorized){
    res.render(views("index.ejs"));
    // log(req.session);
    return;
    // }
    // res.redirect("/login");
})


app.listen(port, (err, data)=>{
    if(err)log(err);
    if(data)log(data);
    log("127.0.0.1:"+port);
})

app.post("/logout",(req, res)=>{
    req.session.destroy(()=>{
        res.redirect("/login");
    })
})
app.get("/logout",(req, res)=>{
    req.session.destroy(()=>{
        res.redirect("/login");
    })
})