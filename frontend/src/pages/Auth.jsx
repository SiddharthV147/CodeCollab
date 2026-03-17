import { React } from "react";
import Login from "../components/Login";
import SignUp from "../components/SignUp";

function Auth() {
    return (
        <>
            <div className="w-screen h-screen flex justify-center items-center">
                <SignUp />
            </div>
        </>
    )
}

export default Auth;