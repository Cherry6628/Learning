import ColorBox from "./ColorBox.jsx";
import ColorPicker from "./ColorPicker.jsx";
import {useState} from "react";
import "./Main.css"

export default function Main() {
    const [color, setColor] = useState("#ff0000");
    return (
        <>
            <ColorPicker color={color} setColor={setColor}></ColorPicker>
            <ColorBox color={color}></ColorBox>
        </>
    );
}
