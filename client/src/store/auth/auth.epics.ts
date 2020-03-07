import { isActionOf, RootEpic } from 'typesafe-actions'
import { filter, mergeMap, map } from 'rxjs/operators'
import { loginAction } from './auth.actions'

export const loginEpic: RootEpic = (action$, _, { api }) =>
  action$.pipe(
    filter(isActionOf(loginAction.request)),
    mergeMap((action) => api.auth.login(action.payload).pipe(map(loginAction.success))),
  )
