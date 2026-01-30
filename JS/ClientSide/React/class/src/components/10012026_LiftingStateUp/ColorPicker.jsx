export default function ColorPicker({color, setColor}) {
    return (
        <>
            <input type="color" defaultValue={color} onChange={(e)=>{setColor(e.target.value)}}></input>
            <p>The Chosen Color is {color}</p>
        </>
    );
}
