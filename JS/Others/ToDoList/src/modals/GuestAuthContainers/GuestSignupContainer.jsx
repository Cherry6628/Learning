import "./GuestContainer.css";
export default function GuestSignupContainer({ setModal, guestCode }) {

    function guestSignup() {
        // toast.success("Guest Signed up");
        console.log("Guest Signed up")
    }
    function getGuestCode(){
        return "X-XXXX-XX"
    }
    if(!guestCode){guestCode=getGuestCode();}

    return (
        <div className="guest-container">
            <div className="guest-header">
                <h1 className="guest-title">Guest Workspace</h1>
                <p className="guest-subtitle">
                    Use this unique code to access your tasks on this browser.
                </p>
            </div>

            <form className="guest-form" onSubmit={(e) => e.preventDefault()}>
                <div className="guest-input-group">
                    <label>Your Guest Code</label>
                    <div className="code-display-box">
                        <span className="code-text">{guestCode}</span>
                        <span className="material-symbols-rounded copy-icon">
                            content_copy
                        </span>
                    </div>
                    <span className="guest-input-hint">
                        Save this code! You'll need it to log back in.
                    </span>
                </div>

                <div className="guest-input-group">
                    <label>Set Password</label>
                    <input type="password" placeholder="Choose a password" />
                </div>

                <button
                    type="submit"
                    className="guest-primary-btn"
                    onClick={guestSignup}
                >
                    Create Guest Account
                    <span className="material-symbols-rounded">
                        rocket_launch
                    </span>
                </button>
            </form>

            <div className="guest-divider">
                <span>OR</span>
            </div>

            <p className="guest-footer">
                Already have a guest code?{" "}
                <a href="#" onClick={() => setModal("guest-login")}>
                    Guest Login
                </a>
            </p>
            <p className="guest-footer secondary">
                Want a real account?{" "}
                <a href="#" onClick={() => setModal("signup")}>
                    Sign Up
                </a>
            </p>
        </div>
    );
}
