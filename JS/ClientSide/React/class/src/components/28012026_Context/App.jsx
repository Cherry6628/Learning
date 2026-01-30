import { useContext } from "react";
import { UserContext } from "./Main";

export default function App() {
    const data = useContext(UserContext);
    
    return <h1>{JSON.stringify(data)}</h1>;
}
