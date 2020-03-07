// import { combineReducers } from 'redux'
// import { IUserModel } from '@interfaces/models'
// import { createUser } from './user.actions'

// const isLoading = createReducer(false)
//   .handleAction([createUser.request], () => true)
//   .handleAction([createUser.success], () => false)

// const user = createReducer(Object.create(null) as IUserModel).handleAction(
//   createUser.success,
//   (_, action) => action.payload
// )

// const error = createReducer(null as Error | null).handleAction(
//   [createUser.failure],
//   (_, action) => action.payload
// )

// const appReducer = combineReducers({
//   isLoading,
//   error,
//   user
// })

// export default appReducer
