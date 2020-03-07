import { loginAction } from '@store/auth/auth.actions';
import { IAuthState } from '@src/interfaces';

import { createReducer } from 'typesafe-actions';

export const initialAuthState: IAuthState = {
  loggedIn: false,
  user: undefined,
  errorMessage: undefined,
  isLoading: false
};

const loginReducer = createReducer(Object.create(null) as IAuthState)
.handleAction([loginAction.request], (state, _ ) => ({...state, isLoading: true}))
.handleAction([loginAction.success], (state, action) => ({...state, loggedIn: true, isLoading: false}))
.handleAction([loginAction.failure], (state, action) => ({...state, errorMessage: action.payload.message, isLoading: false}))

export default loginReducer
