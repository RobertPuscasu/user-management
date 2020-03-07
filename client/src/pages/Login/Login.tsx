import * as React from 'react'
import { useDispatch, useSelector } from 'react-redux'

import LoginForm from '@forms/LoginForm'

import { loginAction} from '@store/auth/auth.actions'
import { ILoginRequest, IRootState, IUserState } from '@src/interfaces'

const Login: React.FC = () => {
  const dispatch = useDispatch()

  const handleSubmit = async (data: ILoginRequest) => {
    await dispatch(loginAction.request(data));
  }

  const { loading } = {loading: false};

  return <LoginForm onSubmit={handleSubmit} isSubmitting={loading} />
}

export default Login
