import "./SignupContainer.css";

export default function SignupContainer({setModal}) {
    function signup(){
        console.log("signup")
    }
    return (
        <div className="signup-container">
            <div className="signup-header">
                <h1 className="signup-title">Create an account</h1>
                <p className="signup-subtitle">Sign up to sync your tasks across all devices.</p>
            </div>

            <form className="signup-form" onSubmit={(e) => e.preventDefault()}>
                <div className="signup-input-group">
                    <label>Full Name</label>
                    <input type="text" placeholder="John Doe" />
                </div>

                <div className="signup-input-group">
                    <label>Email Address</label>
                    <input type="email" placeholder="name@example.com" />
                </div>

                <div className="signup-input-group">
                    <label>Password</label>
                    <input type="password" placeholder="••••••••" />
                    <span className="signup-input-hint">Must be at least 8 characters</span>
                </div>

                <button type="submit" className="signup-primary-btn" onClick={signup}>
                    Create Account 
                    <span className="material-symbols-rounded">arrow_forward</span>
                </button>
            </form>

            <div className="signup-divider">
                <span>OR CONTINUE WITH</span>
            </div>

            <button className="signup-secondary-btn" onClick={()=>setModal("guest-signup")}>
                <span className="material-symbols-rounded">person</span>
                Explore as Guest
            </button>

            <p className="signup-footer">
                Already have an account? <a href="#" onClick={()=>setModal("login")}>Login</a>
            </p>
        </div>
    );
}