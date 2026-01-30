'use strict';

import yargs from 'yargs';
import {hideBin} from 'yargs/helpers';
// import * as name from 'yargs';
const{log}=console;
// log(name)

let args = yargs(hideBin(process.argv));
// log(args)

args.command(
    {
        command: "greet",
        describe: "Greet User.  Use 'node <file> greet --help' to know more",
        aliases: ['g', 'praise'],
        builder:{
            name:{
                describe: "<Username>",
                type: "string",
                demandOption:true
            },
            motivate:{
                describe: "<Username>",
                demandOption:false,
                defalult:false,
                type:"boolean",
                alias:"m"
            }
        },
        handler:function(argv){
            if(argv.motivate)log("Hello, "+argv.name+" ! You can do it...");
            else log("Hello, "+argv.name+" ! ");
        }
    }
)

args.parse();
// log(args)