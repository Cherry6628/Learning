export function Button(props){
    return <button onClick={props.onClick}>Click me - {props.children}</button>;
}