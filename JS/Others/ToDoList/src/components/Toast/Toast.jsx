import React, { useEffect, useRef, useState } from "react";
import "./Toast.css";

export default function Toast({ id, message, type, duration, remove }) {
    const [isVisible, setIsVisible] = useState(true);
    const timerRef = useRef(null);
    const remainingRef = useRef(duration);
    const startRef = useRef(Date.now());

    const startTimer = () => {
        startRef.current = Date.now();
        timerRef.current = setTimeout(() => {
            handleClose();
        }, remainingRef.current);
    };

    const pauseTimer = () => {
        clearTimeout(timerRef.current);
        remainingRef.current -= Date.now() - startRef.current;
    };

    const resumeTimer = () => {
        startTimer();
    };

    const handleClose = () => {
        setIsVisible(false);
        setTimeout(() => remove(id), 400);
    };

    useEffect(() => {
        startTimer();
        return () => clearTimeout(timerRef.current);
    }, []);

    const iconMap = {
        error: "error",
        success: "check_circle",
        info: "info",
        warning: "warning",
    };

    return (
        <div
            className={`toast-container toast-${type} ${
                isVisible ? "slide-in" : "slide-out"
            }`}
            onMouseEnter={pauseTimer}
            onMouseLeave={resumeTimer}
        >
            <div className="toast-content">
                <span className="material-symbols-rounded toast-icon">
                    {iconMap[type]}
                </span>
                <p className="toast-message">{message}</p>
            </div>
            <div
                className="toast-progress"
                style={{ animationDuration: `${duration}ms` }}
            />
        </div>
    );
}
