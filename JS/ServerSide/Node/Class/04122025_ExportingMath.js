function add(a,b){
    return a+b;
}
// named export
export {add};  // old way


// named export
export function sub(a, b){return a-b;}

// default export
// must be only one
export default function greet(){console.log("Welcome")}
