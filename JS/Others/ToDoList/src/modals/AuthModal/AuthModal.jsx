import LoginContainer from "../LoginContainer/LoginContainer";
import SignupContainer from "../SignupContainer/SignupContainer";
import GuestLoginContainer from "../GuestAuthContainers/GuestLoginContainer";
import GuestSignupContainer from "../GuestAuthContainers/GuestSignupContainer";
import { useState } from "react";

export default function AuthModal({defaultModal="signup"}){
    const[modal,setModal]=useState(defaultModal);
    if(modal=="signup")
        return<SignupContainer setModal={setModal}></SignupContainer>
    else if (modal=="login")
        return <LoginContainer setModal={setModal}></LoginContainer>
    else if (modal=="guest-signup")
        return <GuestSignupContainer setModal={setModal}></GuestSignupContainer>
    else if (modal=="guest-login")
        return <GuestLoginContainer setModal={setModal}></GuestLoginContainer>
}