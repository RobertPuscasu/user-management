import { composeWithDevTools } from 'redux-devtools-extension'
import { createBrowserHistory } from 'history'
import { routerMiddleware } from 'connected-react-router'
import { applyMiddleware, createStore } from 'redux'
import epicMiddleware from './middlewares/epicMiddleware'
import storeLogger from './middlewares/storeLogger'
import rootReducer from './rootReducer'

export const history = createBrowserHistory()

const initialState = {}

/** root middleware **/
const composeEnhancers = composeWithDevTools({
  name: 'User Management Store'
})
const rootMiddleware = composeEnhancers(
  applyMiddleware(routerMiddleware(history), epicMiddleware, storeLogger)
)

export const store = createStore(
  rootReducer(history),
  initialState,
  rootMiddleware
)
