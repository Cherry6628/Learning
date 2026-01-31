export default function ProductCard(props){
    return <ul>
        {Object.keys(props).map(
            b=><li>{b}: {props[b]}</li>
        )}
    </ul>
}