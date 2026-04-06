import React from "react";
import MonacoEditorComponent from "../components/MonacoEditorComponent";

function Editor() {
    return (
        <>
            <div className="h-screen w-screen">
                {/* <div>Files</div> */}
                <div className="h-full w-full">
                    <MonacoEditorComponent />
                </div>
            </div>
        </>
    );
}

export default Editor;