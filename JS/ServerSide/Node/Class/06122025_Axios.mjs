//<script [src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js](src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js)"></script>
import axios from 'axios';
const{log}=console;

let r = axios.get("https://httpbin.org/get");
r.then(e=>{
    log(e);
})
// .then(
//     e=>{
//         log(e)
//     }
// )
r = axios.post("https://httpbin.org/status/2342342342342", {
    asdfghjkl:"dsfghjk"
})
r.then(e=>log(e))