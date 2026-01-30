import { useEffect, useState } from "react";

export default function Welcome() {
    useEffect(() => {
        console.log("<Welcome/> Component Loaded");
        return () => {
            console.log("Welcome Component's Effect Unmounted");
        };
    }, []);
    useEffect(() => {
        console.log(
            "Try Updating your JSX File.\nEffect with undefined Dependency will run for every render.",
        );
    });
    const[count,setCount]=useState(0);
    useEffect(()=>{
        console.log("Count Updated.");
        return ()=>{
            console.log("Count Updation Cleanup Code.")
        }
    },[count]);



    const[time,setTime]=useState(0);
    useEffect(()=>{
        console.log("Hello Top");
        let timer=()=>{
            setTime(t=>t+1);
            console.log("Hello")
        }
        let i=setInterval(timer, 3000);
        return ()=>[clearInterval(i), console.log("Cleared Hello")];
    }, [])
    return (
        <div>
            <h1>Hello</h1>
            <p>{count}</p>
            <button onClick={()=>setCount(c=>c+1)}>Increase</button>

            <br/><br/><br/><br/><br/><br/><br/><br/>
            <p>{time}</p>
        </div>
    );
}
