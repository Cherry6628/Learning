// "use strict";
console.log("Hello");
// let os = require("os");
// let fs = require("fs");
// console.log(os);
import fs from "fs";
import os from "os";
// console.log(os.type);
console.log(os.cpus());
// console.log(fs);
fs.writeFileSync("/home/san-zstk426/Educational/Java/JDummy/src/dummy.txt", "Helloooo",
    (e)=>{
        console.log(e);
    }
);

fs.appendFileSync("/home/san-zstk426/Educational/Java/JDummy/src/dummy.txt", "Helloooo",
    (e)=>{
        console.log(e);
    }
);
console.log(fs.readFileSync("/home/san-zstk426/Educational/Java/JDummy/src/dummy.txt","utf-8"))