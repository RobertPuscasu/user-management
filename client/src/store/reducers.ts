import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form'
import { connectRouter } from 'connected-react-router'

import auth from '@store/auth/auth.reducers'

const rootReducer = (history) => combineReducers({ router: connectRouter(history), auth, form: formReducer })


export default rootReducer;
