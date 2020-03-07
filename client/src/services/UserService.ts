import axios from 'axios';
import { IUserModel } from '@src/interfaces'
import { Observable, from } from 'rxjs';
import { endpoints } from './endpoints'
import { map } from 'rxjs/operators';

export class UserService {

  create(user: string): Observable<IUserModel> {
    return from(axios.post<IUserModel>(`${endpoints}`, user))
    .pipe(
      map(res => res.data)
      );
  }
}
