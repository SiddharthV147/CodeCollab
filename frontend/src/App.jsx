import { useState } from 'react'
import Auth from './pages/Auth'
import { Editor } from '@monaco-editor/react'
import MonacoEditorComponent from './pages/Editor'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Editor />
    </>
  )
}

export default App
