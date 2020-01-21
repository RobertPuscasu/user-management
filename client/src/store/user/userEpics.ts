import { isActionOf, RootEpic } from "typesafe-actions";
import { filter, mergeMap, map } from "rxjs/operators";
import { createUser } from "./userActions";

export const createUserEpic: RootEpic = (action$, _, { api }) =>
  action$.pipe(
    filter(isActionOf(createUser.request)),
    mergeMap(action =>
      api.users.create(action.payload).pipe(map(createUser.success))
    )
  );