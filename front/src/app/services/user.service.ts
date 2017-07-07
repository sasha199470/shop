import {Injectable} from '@angular/core';
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

import {RequestMapping} from "../request-mapping";
import {User} from "../domain/user";
import {Product} from "../domain/product";
import {ErrorHandler} from "../utility/error-handler";

@Injectable()
export class UserService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  getCurrentUser(): Promise<User> {
    return this.http
      .get(RequestMapping.getCurrentUser, {headers: this.headers})
      .toPromise()
      .then(response => response.json());
  }

  addProductToBasket(id:number): Promise<any> {
    let url = RequestMapping.addToBasket.replace('{id}', id.toString());
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .catch(ErrorHandler.handleError)
  }

  getBasket(): Promise<Product[]> {
    return this.http
      .get(RequestMapping.getBasket, {headers: this.headers})
      .toPromise()
      .then(products => products.json() as Product[])
      .catch(ErrorHandler.handleError)
  }
}
