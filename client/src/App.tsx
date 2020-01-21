import React from 'react'
import './App.css'
import { createBrowserHistory } from 'history'

type AppProps = {
  history: ReturnType<typeof createBrowserHistory>
}
export const App: React.FunctionComponent<AppProps> = (props: AppProps) => {
  return (
    <div>
      <p>Hello from React!</p>
    </div>
  )
}
