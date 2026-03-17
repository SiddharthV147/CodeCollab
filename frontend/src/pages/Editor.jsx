import React from "react";
import MonacoEditorComponent from "../components/MonacoEditorComponent";
import { Panel, PanelGroup, ResizeHandle } from "react-resizable-panels";

function Editor() {
    return (
        <div className="h-screen w-full">
            <PanelGroup direction="horizontal">
                {/* Sidebar Panel */}
                <Panel defaultSize={20} minSize={10} maxSize={40}>
                    <div className="h-full bg-gray-100 p-2">
                        Files
                    </div>
                </Panel>

                <ResizeHandle className="w-2 bg-gray-300 hover:bg-blue-500 cursor-col-resize transition-colors" />

                <Panel defaultSize={80}>
                    <div className="h-full">
                        <MonacoEditorComponent />
                    </div>
                </Panel>
            </PanelGroup>
        </div>
    );
}

export default Editor;