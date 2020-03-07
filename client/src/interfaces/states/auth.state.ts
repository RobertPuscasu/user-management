import { IUserModel } from '@src/interfaces'

export interface IAuthState {
  loggedIn: boolean
  user: IUserModel
  errorMessage: string
  isLoading: boolean
}
