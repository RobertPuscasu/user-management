import { combineReducers } from 'redux'
import { connectRouter } from 'connected-react-router'

import userReducer from './user/userReducer'

const rootReducer = history =>
  combineReducers({
    router: connectRouter(history),
    user: userReducer
  })

export default rootReducer
