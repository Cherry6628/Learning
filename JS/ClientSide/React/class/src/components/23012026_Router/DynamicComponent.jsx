import { useParams } from "react-router-dom";
export default function DynamicComponent() {
    const params = useParams();
    return (
        <h1>
            {JSON.stringify(params)}
        </h1>
    );
}
