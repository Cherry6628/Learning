import "./App.css";
import { Toaster } from "react-hot-toast";
// import TaskDashboard from "../pages/TaskDashboard/TaskDashboard.jsx";
import TaskDashboard from "./pages/TaskDashboard/TaskDashboard.jsx";
import AuthPage from "./pages/AuthPage/AuthPage.jsx"
function App() {
    return (
        <><Toaster position="top-right" />
        {/* <StatContainer></StatContainer>
            <Task
                completion="3"
                total="8"
                color="#3b82f6"
                // bgcolor={util.dim("#3b82f6")}
                tag="Daily"
                title="Drink Water"
                description="To Stay Healthy"
                icon="water_drop"
            ></Task>
            <NewTask></NewTask> */}
            {/* <SignupContainer></SignupContainer>
            <LoginContainer></LoginContainer>
            <GuestLoginContainer></GuestLoginContainer>
            <GuestSignupContainer></GuestSignupContainer> */}
            {/* <AuthModal></AuthModal> */}
            {/* <AuthPage></AuthPage> */}
            <TaskDashboard></TaskDashboard>
            
        </>
    );
}

export default App;
