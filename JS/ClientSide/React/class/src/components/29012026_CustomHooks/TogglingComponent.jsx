import { useToggle } from "./Hooks";
export default function TogglingComponent(){
    const [value,toggleValue]=useToggle();
    return <>
        <div style={{
            backgroundColor: value?"#111":"#ddd",
            color: value?"#ddd":"#111",
            height: "100px",
            border: "1px solid black"
        }}>
            <h1>{value?"Dark Theme Enabled":"Light Theme Enabled"}</h1>
            <button onClick={toggleValue}>Toggle Theme</button>
        </div>
    </>;
}