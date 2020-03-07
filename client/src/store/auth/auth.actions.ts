import { IUserModel, ILoginRequest } from '@interfaces/index'
import { createAsyncAction } from 'typesafe-actions'

export const loginAction = createAsyncAction(
  'loginAction.request',
  'loginAction.success',
  'loginAction.failure',
  'loginAction.cancel',
)<ILoginRequest, String, Error, undefined>()
