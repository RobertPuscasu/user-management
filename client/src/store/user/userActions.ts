import { IUserModel } from '@interfaces/models'
import { createAsyncAction } from 'typesafe-actions'

export const createUser = createAsyncAction(
  'createUser.request',
  'createUser.success',
  'createUser.failure'
)<string, IUserModel, Error, undefined>()
