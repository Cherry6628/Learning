import "./TaskDashboard.css";
import Task from "../../components/Task/Task.jsx";
import NewTask from "../../components/Task/NewTask.jsx";
import StatContainer from "../../components/Stat/StatContainer.jsx";
import Sidebar from "../../components/Sidebar/Sidebar.jsx";

export default function TaskDashboard() {
    let tasks = [
        {
            completion: 3,
            total: 8,
            color: "#3b82f6",
            tag: "Daily",
            title: "Drink Water",
            description: "To Stay Healthy",
            icon: "water_drop",
        },
        {
            completion: 0,
            total: 1,
            color: "#f97316",
            tag: "Work",
            title: "Email Marketing Team",
            description: "Send weekly analytics update",
            icon: "mail",
        },
        {
            completion: 15,
            total: 50,
            color: "#a855f7",
            tag: "Fitness",
            title: "Push-ups",
            // description: "",
            icon: "fitness_center",
        },
        {
            completion: 0,
            total: 1,
            color: "#22c55e",
            tag: "Personal",
            title: "Read Books",
            description: "\"Atomic Habits\" - Chapter 4",
            icon: "book",
        },
    ];
    let tags = [
        {
            name: "Personal",
            color: "#f97316"
        },
        {
            name: "Work",
            color: "#00ced1"
        }
    ]
    let profile = {
        email: "cherrycherry6628@gmail.com kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",
        name: "Cherry6628"
    }
    return (
        <div className="dashboard-wrapper">
            <Sidebar tagsList={tags} profile={profile}></Sidebar>
            <main className="dashboard-main">
                <header className="dashboard-header">
                    <div className="header-left">
                        <h1>My Tasks</h1>
                        <div className="search-bar">
                            <span className="material-symbols-rounded">
                                search
                            </span>
                            <input
                                type="text"
                                placeholder="Find a task..."
                                className="find-task"
                            />
                        </div>
                    </div>
                    <div className="header-right">
                        <button className="add-task-btn">
                            <span className="material-symbols-rounded">
                                add
                            </span>
                            Add Task
                        </button>
                    </div>
                </header>

                <section className="dashboard-content">
                    <StatContainer completion="75" streak="12" tasks="12"></StatContainer>

                    <div className="tasks-section">
                        <h2>Today's Tasks</h2>
                        <div className="tasks-grid">
                            {tasks.map((b,idx) => (
                                <Task {...b} key={idx}></Task>
                            ))}
                            <NewTask></NewTask>
                        </div>
                    </div>
                </section>
            </main>
        </div>
    );
}
