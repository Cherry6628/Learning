import "./AuthPage.css";
import Logo from "../../components/Logo/Logo.jsx";
import AuthModal from "../../modals/AuthModal/AuthModal.jsx";

export default function AuthPage() {
    return (
        <div className="auth-page-wrapper">
            <header className="auth-navbar">
                <div className="auth-nav-logo">
                    <Logo showTagline={false} />
                </div>
            </header>

            <main className="auth-main-content">
                <div className="auth-branding-section">
                    <div className="auth-illustration-container">
                        <div className="auth-mockup-card">
                            <div className="auth-mockup-check">
                                <span className="material-symbols-rounded">
                                    check_circle
                                </span>
                            </div>
                            <div className="auth-mockup-lines">
                                <div className="auth-m-line auth-m-line-sm"></div>
                                <div className="auth-m-line auth-m-line-lg"></div>
                                <div className="auth-m-line auth-m-line-lg"></div>
                            </div>
                        </div>
                    </div>
                    <div className="auth-branding-text">
                        <h2>Organize with precision.</h2>
                        <p>
                            Join over 50,000 users who manage their daily
                            repetition, task counting, and categories with
                            TaskMaster.
                        </p>
                    </div>
                </div>

                <div className="auth-form-section">
                    <AuthModal />
                </div>
            </main>

            <footer className="auth-footer-bar">
                <p className="auth-copyright-text">
                    ©&nbsp;2026&nbsp;TaskMaster&nbsp; All&nbsp;rights&nbsp;reserved.
                </p>
                <div className="auth-legal-links">
                    <a href="#terms">Terms of Service</a>
                    <a href="#privacy">Privacy Policy</a>
                    <a href="#cookies">Cookie Settings</a>
                </div>
            </footer>
        </div>
    );
}
