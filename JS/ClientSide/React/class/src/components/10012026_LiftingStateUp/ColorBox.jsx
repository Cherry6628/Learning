export default function ColorBox({color}){
    let style={
        width: "100px",
        aspectRatio:"1/1",
        backgroundColor:color
    }
    return <div style={style}></div>
}
