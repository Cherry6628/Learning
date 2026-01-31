import { useReducer } from "react";

export default function Todo() {
    function reducer(state, action) {
        if (action.type == "add") {
            if (state.task.trim() === "") return state;
            return { task: "", tasks: [...state.tasks, state.task] };
        } else if (action.type == "del") {
            return {
                ...state,
                tasks: state.tasks.filter((b, i) => i != action.value),
            };
        } else if ((action.type = "updt")) {
            if (action.value.trim() === "") return state;
            return { ...state, task: action.value };
        }
        return state;
    }

    const [state, dispatch] = useReducer(reducer, { task: "", tasks: [] });

    return (
        <div style={{ padding: 20, maxWidth: 400 }}>
            <h2> To-Do List</h2>

            <input
                type="text"
                value={state.task}
                placeholder="Enter a task"
                onChange={(e) =>
                    dispatch({ type: "updt", value: e.target.value })
                }
            />
            <button onClick={(e) => dispatch({ type: "add" })}>Add</button>

            <ul>
                {state.tasks.map((t, index) => (
                    <li key={index}>
                        {t}
                        <button
                            onClick={() =>
                                dispatch({ type: "del", value: index })
                            }
                            style={{ marginLeft: 10 }}
                        >
                            Delete
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
