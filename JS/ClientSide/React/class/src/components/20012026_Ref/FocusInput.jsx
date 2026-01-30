import {useRef} from "react";

export default function FocusInput(){
    const ref=useRef(null);
    console.log(ref);
    return <div>
        {/* <script>console.log({ref})</script> */}
        <input ref={ref} style={{border: "1px solid red"}}></input>
        <button onClick={()=>{ref.current.focus();console.log(ref)}}>Focus</button>
    </div>
}