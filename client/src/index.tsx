import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'

import './index.css'
import { App } from './App'

import { store, history } from './store/configureStore'

render(
  <Provider store={store}>
    <App history={history} />
  </Provider>,
  document.getElementById('root')
)
