import yargs from 'yargs';
import {hideBin} from 'yargs/helpers';
const{log}=console;
let args = yargs(hideBin(process.argv));
args.command({
    command: "add",
    describe: "Pass Two Arguments -a and -b to add them.",
    builder:{
        a:{
            describe: "Number 1 to add",
            type: "number",
            demandOption:true,
        },
        b:{
            describe:"Number 2 to add",
            type:"number",
            demandOption:false,
            default:0
        }
    },
    handler: function(args){
        log(args.a+" + "+args.b+" = "+(args.a+args.b));
    }
})
args.command({
    command: "sub",
    describe: "Pass Two Arguments -a and -b to subtract b from a.",
    builder:{
        a:{
            describe: "Number 1",
            type: "number",
            demandOption:true,
        },
        b:{
            describe:"Number 2 to subtract from Number 1",
            type:"number",
            demandOption:false,
            default:0
        }
    },
    handler: function(args){
        log(args.a+" - "+args.b+" = "+(args.a-args.b));
    }
})
args.command({
    command: "mul",
    describe: "Pass Two Arguments -a and -b to multiply them.",
    builder:{
        a:{
            describe: "Number 1 to multiply",
            type: "number",
            demandOption:true,
        },
        b:{
            describe:"Number 2 to multiply",
            type:"number",
            demandOption:false,
            default:1
        }
    },
    handler: function(args){
        log(args.a+" x "+args.b+" = "+(args.a*args.b));
    }
})
args.command({
    command: "div",
    describe: "Pass Two Arguments -a and -b to divide a by b.",
    builder:{
        a:{
            describe: "Number 1",
            type: "number",
            demandOption:true,
        },
        b:{
            describe:"Number 2 divide from Number 1",
            type:"number",
            demandOption:false,
            default:1
        }
    },
    handler: function(args){
        log(args.a+" / "+args.b+" = "+(args.a/args.b));
    }
})
log("Initiated")
args.parse();