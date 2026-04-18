import React, { useRef, useState } from 'react';
import Editor from '@monaco-editor/react';

function MonacoEditorComponent() {
  const [code, setCode] = useState('// type your code here');
  const editorRef = useRef(null);

  // handle each change 
  function handleEditorDidMount(editor) {
    editorRef.current = editor;
    
    editor.onDidChangeModelContent((event) => {
      event.changes.forEach((change) => {
        const insertedText = change.text;
        const offset = change.rangeOffset; 
        const deletedLength = change.rangeLength;
        if (insertedText.length > 0) {
          for (let i = 0; i < insertedText.length; i++) {
            console.log(`Character: "${insertedText[i]}", Index: ${offset + i}`);
          }
        } else if(deletedLength > 0) {
          for (let i = 0; i < deletedLength; i++) {
            console.log(`DELETED at Index: ${offset + i}`);
          }
        }
      });
    });
  }

  function handleEditorChange(value, event) {
    setCode(value); 
  }

  return (
    <div className='h-screen w-full'>
      <Editor
        height="90%" 
        language="javascript" 
        theme="vs-dark" 
        value={code} 
        onChange={handleEditorChange} 
        onMount={handleEditorDidMount}
        options={{
          minimap: { enabled: false }, 
          fontSize: 14,
        }}
      />
    </div>
  );
}

export default MonacoEditorComponent;
