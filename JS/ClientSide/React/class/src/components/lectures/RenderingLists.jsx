export default function RenderList(){
    let d = ["JSX", "Props", "State", "Hooks"];
    return <ul>
        {d.map(b=><li>{b}</li>)}
    </ul>;
}