
export default function Icon({ size = "44px" }) {
    return (
        <div 
            style={{
                width: size,
                height: size,
                backgroundColor: 'var(--action-color)',
                borderRadius: '12px',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                flexShrink: 0
            }}
        >
            <svg 
                width="60%" 
                height="60%" 
                viewBox="0 0 24 24" 
                fill="none" 
                stroke="white" 
                strokeWidth="3" 
                strokeLinecap="round" 
                strokeLinejoin="round"
            >
                <circle cx="12" cy="12" r="10" />
                <path d="m9 12 2 2 4-4" />
            </svg>
        </div>
    );
}