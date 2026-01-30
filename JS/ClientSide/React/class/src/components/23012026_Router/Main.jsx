import { BrowserRouter, Routes, Route, Link, useNavigate } from "react-router-dom";
import Index from "./Index";
import DynamicComponent from "./DynamicComponent";


export default function Main() {
    // function goHome(){navigate("/");}
    return (
        <>
            <BrowserRouter>
            {/* { */}
    {/* // const navigate=useNavigate();} */}
            {/* <button onClick={goHome}>Go to /</button> */}
                <Routes>
                    <Route path="/" element={<Index data="Index" />}></Route>
                    <Route
                        path="/accounts"
                        element={<Index data="Accounts" />}
                    ></Route>
                    <Route
                        path="*"
                        element={<Index data="Not Found" />}
                    ></Route>

                    <Route path="/profile">
                        <Route
                            path="page"
                            element={<Index data="Profile Page" />}
                        ></Route>
                        <Route
                            path="name"
                            element={<Index data="Profile Name" />}
                        ></Route>
                    </Route>

                    <Route
                        path="/dynamic/:id"
                        element={<DynamicComponent />}
                    ></Route>
                </Routes>
            </BrowserRouter>
        </>
    );
}
