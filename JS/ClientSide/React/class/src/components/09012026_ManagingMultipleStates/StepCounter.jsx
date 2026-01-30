import { useState } from "react";


export default function StepCounter(){
    // const[count,setCount]=useState(0);
    // const[show,setShow]=useState(true);
    const [config,setConfig]=useState({
        count: 0,
        show:true
    });
    function handleIncrement(){
        setConfig({
            ...config,
            count:config.count+1
        })
    }
    function handleShow(){
        setConfig({
            ...config,
            show:!config.show
        })
    }

    return (
        <div>
            {config.show?<p>Count: {config.count}</p>:""}
            <button onClick={handleIncrement}>Increment</button>
            <button onClick={handleShow}>Show/Hide</button>
        </div>
    )
}