import { FormStateMap } from 'redux-form'
import { RouterState } from 'connected-react-router'

import  { IUserState } from '@src/interfaces'

export interface IRootState {
  user: IUserState
  form: FormStateMap
  router: RouterState
}
