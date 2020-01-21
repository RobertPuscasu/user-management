import { createEpicMiddleware } from 'redux-observable'
import { RootAction, RootState, Services } from 'typesafe-actions'
import services from 'services'

const epicMiddleware = createEpicMiddleware<
  RootAction,
  RootAction,
  RootState,
  Services
>({
  dependencies: services
})

export default epicMiddleware
