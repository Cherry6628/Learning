import utils from "../../utils/utils";
import "./Stat.css";

export default function Stat({ label, value, icon, color, valueName}) {
    return (
        <div className="stat-card">
            <div className="stat-icon-wrapper" style={{backgroundColor:utils.dim(color,0.1)}}>
                <span className="material-symbols-rounded stat-icon" style={{color:color}}>
                    {icon}
                </span>
            </div>
            <div className="stat-content">
                <span className="stat-label">{label}</span>
                <h2 className="stat-value">{value} <span className="stat-value-name">{valueName||""}</span></h2>
            </div>
        </div>
    );
}
