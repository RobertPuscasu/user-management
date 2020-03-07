import { combineEpics } from 'redux-observable';

import * as userEpics from '@store/user/user.epics'
import * as authEpics from '@store/auth/auth.epics'

export default combineEpics(
  ...Object.values(userEpics),
  ...Object.values(authEpics)
)
