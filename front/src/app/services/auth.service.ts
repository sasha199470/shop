import {Injectable} from '@angular/core';
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';

import {UserRequest} from '../domain/request/user-request';
import {RequestMapping} from "../request-mapping";
import {ErrorHandler} from "../utility/error-handler";
import {User} from "../domain/user";

@Injectable()
export class AuthService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http:Http) {
  }

  register(userRequest:UserRequest):Promise<User> {
    return this.http
      .post(RequestMapping.register, JSON.stringify(userRequest), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(ErrorHandler.handleError)
  }

  signIn(userRequest:UserRequest):Promise<User> {
    return this.http
      .post(RequestMapping.login, JSON.stringify(userRequest), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(ErrorHandler.handleError)
  }

  logOut():Promise<any> {
    return this.http
      .get(RequestMapping.logout, {headers: this.headers})
      .toPromise()
      .catch(ErrorHandler.handleError)
  }
}
