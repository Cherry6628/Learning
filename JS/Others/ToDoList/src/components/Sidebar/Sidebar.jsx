import React, { useState } from "react";
import Logo from "../../components/Logo/Logo.jsx";
import "./Sidebar.css";

export default function Sidebar({tagsList, profile}) {
    const [isOpen, setOpen] = useState(false);

    return (
        <>
            <button className="hamburger-btn" onClick={() => setOpen(true)}>
                <span className="material-symbols-rounded">menu</span>
            </button>

            <aside className={`dashboard-sidebar ${isOpen ? "open" : ""}`}>
                <div className="sidebar-header">
                    <Logo showTagline={true} />
                    <button
                        className="close-sidebar-btn"
                        onClick={() => setOpen(false)}
                    >
                        <span className="material-symbols-rounded">close</span>
                    </button>
                </div>

                <nav className="sidebar-nav">
                    <div className="top-nav-group">
                        <a href="#" className="nav-item active">
                            <span className="material-symbols-rounded">grid_view</span>
                            My Tasks
                        </a>
                        <a href="#" className="nav-item">
                            <span className="material-symbols-rounded">history</span>
                            History
                        </a>
                        <a href="#" className="nav-item">
                            <span className="material-symbols-rounded">manage_accounts</span>
                            Manage Account
                        </a>
                    </div>

                    {tagsList&&tagsList!=[]&&<div className="categories-scroll-area">
                        <div className="sidebar-divider">CATEGORIES</div>
                        <div className="nav-group">
                            {tagsList.map((b,idx)=> <a key={idx} href="#" className="nav-item category">
                                <span className="dot" style={{"--tag-color": b.color}}></span>
                                {b.name}
                            </a>)}
                        </div>
                    </div>}
                </nav>

                <div className="sidebar-footer">
                    <button className="logout-btn">
                        <span className="material-symbols-rounded">logout</span>
                        Logout
                    </button>
                    <div className="user-profile">
                        <div className="user-avatar">AM</div>
                        <div className="user-info">
                            <span className="user-name">{profile.name}</span>
                            <span className="user-email">{profile.email}</span>
                        </div>
                    </div>
                </div>
            </aside>
            {isOpen && (
                <div className="sidebar-overlay" onClick={() => setOpen(false)}></div>
            )}
        </>
    );
}