import { StateType, ActionType } from 'typesafe-actions'
import { Epic } from 'redux-observable'

declare module 'typesafe-actions' {
  export type Store = StateType<typeof import('./store').store>
  export type RootState = StateType<
    ReturnType<typeof import('./reducers').default>
  >
  export type RootAction = ActionType<typeof import('./actions').default>
  export type Services = typeof import('../services/index').default
  export type RootEpic = Epic<RootAction, RootAction, RootState, Services>

  interface Types {
    RootAction: RootAction
  }
}
