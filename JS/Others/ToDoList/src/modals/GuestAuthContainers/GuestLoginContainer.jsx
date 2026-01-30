import "./GuestContainer.css";

export default function GuestLoginContainer({setModal}) {
    function guestLogin(){
        console.log("guestLogin");
        
    }
    return (
        <div className="guest-container">
            <div className="guest-header">
                <h1 className="guest-title">Guest Login</h1>
                <p className="guest-subtitle">Enter your guest credentials to continue.</p>
            </div>

            <form className="guest-form" onSubmit={(e) => e.preventDefault()}>
                <div className="guest-input-group">
                    <label>Guest Code</label>
                    <input type="text" placeholder="e.g. G-XXXX-XX" style={{textTransform: "uppercase"}}/>
                </div>

                <div className="guest-input-group">
                    <label>Password</label>
                    <input type="password" placeholder="••••••••" />
                </div>

                <button type="submit" className="guest-primary-btn" onClick={guestLogin}>
                    Login as Guest
                    <span className="material-symbols-rounded">login</span>
                </button>
            </form>

            <div className="guest-divider">
                <span>OR</span>
            </div>

            <p className="guest-footer">
                New guest? <a href="#" onClick={()=>setModal("guest-signup")}>Get Guest Code</a>
            </p>
            <p className="guest-footer secondary">
                Have a permanent account? <a href="#" onClick={()=>setModal("login")}>Login</a>
            </p>
        </div>
    );
}