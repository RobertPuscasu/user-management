import { ILoginRequest } from '@interfaces/requests/login.request'
import axios from 'axios'
import { Observable, from } from 'rxjs'
import { endpoints } from './endpoints'
import { map } from 'rxjs/operators'

export class AuthService {
  public login(login: ILoginRequest): Observable<string> {
    return from(axios.post<string>(`${endpoints.login}`, login)).pipe(map((res) => res.data))
  }
}
