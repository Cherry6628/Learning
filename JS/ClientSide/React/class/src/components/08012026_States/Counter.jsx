import { useState } from "react";


export default function Counter(){
    const[count,setCount]=useState(0);
    const[show,setShow]=useState(true);
    // let count=0;
    function handleClick(){
        // count++;
        console.clear();
        
        setCount(count+1); // works
        setCount(count+1); //  no
        setCount(count=>count+1);  // works
        console.log("Count Value: "+count);

    }
    return (
        <div>
            {show?<p>Count: {count}</p>:""}
            <button onClick={handleClick}>Increment</button>
            <button onClick={()=>{setShow(!show)}}>Show/Hide</button>
        </div>
    )
}