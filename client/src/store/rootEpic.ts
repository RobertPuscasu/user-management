import { combineEpics } from 'redux-observable'

import * as userEpics from './user/userEpics'

export default combineEpics(...Object.values(userEpics))
