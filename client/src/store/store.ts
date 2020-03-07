import { createBrowserHistory } from 'history'
import { applyMiddleware, compose, createStore } from 'redux'
import { routerMiddleware } from 'connected-react-router'

import rootReducer from '@store/reducers'
import epics from '@store/epics'
import middlewares from '@store/middleware'

const composeEnhancers =
  process.env.NODE_ENV !== 'production' && (window as any).__REDUX_DEVTOOLS_EXTENSION_COMPOSE__
    ? (window as any).__REDUX_DEVTOOLS_EXTENSION_COMPOSE__
    : compose
const initialState = {}

export const history = createBrowserHistory()
export const store = createStore(
  rootReducer(history),
  initialState,
  composeEnhancers(applyMiddleware(routerMiddleware(history), middlewares)),
)

middlewares.run(epics)
