import React from 'react';
import Icon from './Icon.jsx';

export default function Logo({ showTagline = true }) {
    return (
        <div style={{ 
            display: 'flex', 
            alignItems: 'center', 
            gap: '12px',
            userSelect: 'none' 
        }}>
            <Icon size="40px" />
            
            <div style={{ 
                display: 'flex', 
                flexDirection: 'column', 
                lineHeight: '1.1' 
            }}>
                <span style={{ 
                    fontSize: '22px', 
                    fontWeight: '800', 
                    color: 'var(--text-main)',
                    letterSpacing: '-0.5px'
                }}>
                    TaskMaster
                </span>
                
                {showTagline && (
                    <span style={{ 
                        fontSize: '12px', 
                        fontWeight: '600', 
                        color: 'var(--text-dim)',
                        opacity: '0.8'
                    }}>
                        Productivity Pro
                    </span>
                )}
            </div>
        </div>
    );
}