import React, { useState } from 'react';
import Editor from '@monaco-editor/react';

function MonacoEditorComponent() {
  const [code, setCode] = useState('// type your code here');

  function handleEditorChange(value, event) {
    setCode(value); // Update the state with the new code value
  }

  return (
    <div className='h-screen w-full'>
      <Editor
        height="100%" // Set height to fill the container
        language="javascript" // Default language (e.g., "javascript", "typescript", "python")
        theme="vs-dark" // Default theme (e.g., "vs-light", "vs-dark")
        value={code} // The current code value
        onChange={handleEditorChange} // Callback for when content changes
        options={{
          minimap: { enabled: false }, // Customize editor options
          fontSize: 14,
        }}
      />
    </div>
  );
}

export default MonacoEditorComponent;
