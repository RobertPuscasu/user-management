import { IUserModel } from '@interfaces/models'
import { createAsyncAction } from 'typesafe-actions'

export const createUser = createAsyncAction(
  '[Create User API] Request',
  '[Create User API] Success',
  '[Create User API] Failure'
)<string, IUserModel, Error, undefined>()
