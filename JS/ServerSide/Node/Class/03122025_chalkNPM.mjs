// let chalk = require("chalk");
import c from "chalk";
import colour from "colour";
const {log}=console;
log(c.blue("Hello World"));
log(c.black.bgGreen("Hello"));
log(`CPU: ${c.red('90%')}\nRAM: ${c.green('40%')}\nDISK: ${c.yellow('70%')}`);


log(c.rgb(100, 190, 255)("Hello"));


colour.setTheme({
  silly: 'rainbow',
  input: 'grey',
  verbose: 'cyan',
  prompt: 'grey',
  info: 'green',
  data: 'grey',
  help: 'cyan',
  warn: ['yellow', 'underline'], // Applies two styles at once
  debug: 'blue',
  error: 'red bold' // Again, two styles
});
 
console.log("this is an error".error); // outputs bold red text
console.log("this is a warning".warn); // outputs underlined yellow text
 
console.log(colour.green("this is green")); // Alternatively