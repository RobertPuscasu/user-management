import 'normalize.css'
import '@style/base.styl'
import * as React from 'react'
import { render } from 'react-dom'
import { ConnectedRouter } from 'connected-react-router'
import { Provider } from 'react-redux'
import './i18n'

import App from './App'

import { store, history } from '@src/store/store'
import { requestInterceptor } from './axios';
requestInterceptor()
const app =
  (<Provider store={store}>
    <ConnectedRouter history={history}>
      <App />
    </ConnectedRouter>
  </Provider>)


render(app, document.getElementById('root'))
