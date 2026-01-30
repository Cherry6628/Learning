import React, { useEffect, useState } from 'react';
import './Toast.css';

export default function Toast({ message, duration = 3000, onClose }) {
    const [isVisible, setIsVisible] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => {
            setIsVisible(false);
        }, duration);

        const removeTimer = setTimeout(() => {
            onClose();
        }, duration + 500); 

        return () => {
            clearTimeout(timer);
            clearTimeout(removeTimer);
        };
    }, [duration, onClose]);

    return (
        <div className={`toast-container ${isVisible ? 'slide-in' : 'slide-out'}`}>
            <div className="toast-content">
                <span className="material-symbols-rounded toast-icon">error</span>
                <p className="toast-message">{message}</p>
            </div>
            <div 
                className="toast-progress" 
                style={{ animationDuration: `${duration}ms` }}
            ></div>
        </div>
    );
}