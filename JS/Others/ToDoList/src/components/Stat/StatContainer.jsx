import Stat from "./Stat.jsx";
import "./StatContainer.css";
export default function StatContainer({completion, tasks, streak}){
    return <div className="stat-container" style={{ display: "flex", gap: "16px", padding: "20px" }}>
    <Stat 
        label="Completion Rate" 
        value={completion||"0"}
        valueName="%"
        icon="trending_up" 
        color="#00ced1" 
        // bgcolor="#eefdfd" 
    />
    <Stat 
        label="Tasks Done" 
        value={tasks||"0"}
        valueName="Tasks"
        icon="event_available" 
        color="#ff5722" 
        // bgcolor="#fff4f0" 
    />
    <Stat 
        label="Current Streak" 
        value={streak||"0"}
        valueName="Days" 
        icon="local_fire_department" 
        color="#3b82f6" 
        // bgcolor="#eff6ff" 
    />
</div>
}