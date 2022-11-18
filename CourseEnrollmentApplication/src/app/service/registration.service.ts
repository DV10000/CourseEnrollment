import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  constructor(private http: HttpClient) {}

  public loginUserFormRemote(user: User): Observable<any> {
    return this.http
      .get<any>(
        'http://localhost:8080/login?userLoginId=' +
          user.userLoginId +
          '&password=' +
          user.password
      ).pipe(
        map((user) =>{
          console.log('inside login');
          localStorage.setItem('currentUser', JSON.stringify(user));
          return user;
        })
      )
    
    }

  public registerUserFormRemote(user: User): Observable<any> {
    return this.http.post<any>('http://localhost:8080/register/user', user);
  }

  public forgotPassword(user: User): Observable<any> {
    return this.http.put<any>('http://localhost:8080/update/password', user);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
}
