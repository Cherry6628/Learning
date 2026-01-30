import { Button } from "./Button"
function handler(){
    console.log(Math.random());
}
export function StudentCard({name,course,temp}){
    console.log("hello")
    return (
    <div> Student Card Component 
        <p>Name: {name}</p>
        <p>Course: {course}</p>
        <Button onClick={handler}>Hello</Button>
        <Button children="Overridden by Hello">Hello</Button>

    </div>
);
}