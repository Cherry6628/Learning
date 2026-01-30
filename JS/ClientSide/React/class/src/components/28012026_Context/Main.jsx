import App from "./App";
import { createContext } from "react";
export const UserContext = createContext();

export default function Main(){
    return <UserContext.Provider value={{name:"Sanjee"}}>
        <App/>
    </UserContext.Provider>
}