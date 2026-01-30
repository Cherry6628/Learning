import * as all from "./04122025_ExportingMath.js";
import defaultMethod from "./04122025_ExportingMath.js";
const{log}=console;


log(defaultMethod);
log(defaultMethod());
log("Hello");
log(all);

let args = process.argv;
log(args);

log("Hello, "+args[2]);