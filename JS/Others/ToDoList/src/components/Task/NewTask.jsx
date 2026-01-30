import "./NewTask.css";

export default function NewTask({ onClick }) {
    return (
        <button className="new-task-card" onClick={onClick}>
            <div className="new-task-content">
                <div className="new-task-icon-circle">
                    <span className="material-symbols-rounded">add</span>
                </div>
                <span className="new-task-text">Create New Task</span>
            </div>
        </button>
    );
}