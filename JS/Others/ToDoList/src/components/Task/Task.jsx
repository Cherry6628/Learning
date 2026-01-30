import { useState } from "react";
import utils from "../../utils/utils.js";
import "./Task.css";
export default function Task(prop) {
    const [taskConfig, setTaskConfig] = useState({
        completion: prop.completion || 0,
        total: prop.total || 1,
        description: prop.description || " ",
        title: prop.title || "Untitled",
        tag: prop.tag,
        color: prop.color,
        icon: prop.icon || "flag",
    });
    function handleCompletion() {
        if (taskConfig.completion >= taskConfig.total) return;
        setTaskConfig((prev) => ({
            ...prev,
            completion: Math.max(0, +prev.completion + 1),
        }));
    }
    return (
        <div
            className="task-card"
            style={{
                "--theme-color1": taskConfig.color,
                "--theme-color2": utils.dim(taskConfig.color, 0.1),
            }}
        >
            <div className="task-header">
                <div className="task-icon-wrapper">
                    <span className="material-symbols-rounded main-task-icon">
                        {taskConfig.icon}
                    </span>
                </div>
                <div className="task-header-actions">
                    <button className="task-edit-btn">
                        <span className="material-symbols-rounded">edit</span>
                    </button>
                    {taskConfig.tag && (
                        <div className="task-tag-container">
                            <span className="task-tag-label">
                                {taskConfig.tag}
                            </span>
                        </div>
                    )}
                </div>
            </div>

            <div className="task-body">
                <h2 className="task-title">{taskConfig.title || "Untitled"}</h2>
                <p className="task-description">
                    {taskConfig.description || " "}
                </p>
            </div>
            <div className="task-footer">
                <button className="task-add-btn" onClick={handleCompletion}>
                    <span className="material-symbols-rounded">
                        {taskConfig.total-taskConfig.completion > 1 ? "add" : "check"}
                    </span>
                </button>

                {taskConfig.total > 1 && (
                    <div className="progress-section">
                        <div className="progress-labels">
                            <span className="label-text">Progress</span>
                            <span className="progress-count">
                                {taskConfig.completion}/{taskConfig.total}{" "}
                                &nbsp;
                            </span>
                        </div>
                        <div className="progress-bar-container">
                            <div
                                className="progress-bar-fill"
                                style={{
                                    width: `${
                                        (taskConfig.completion /
                                            taskConfig.total) *
                                        100
                                    }%`,
                                }}
                            ></div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}
