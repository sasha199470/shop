import {Injectable} from '@angular/core';
import {Http, Headers} from "@angular/http";
import {RequestMapping} from "../request-mapping";
import {Category} from "../domain/category";
import {ErrorHandler} from "../utility/error-handler";

@Injectable()
export class CatalogService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http:Http) {
  }

  getCatalogs():Promise<Category[]> {
    return this.http
      .get(RequestMapping.getCatalogs, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Category[])
      .catch(ErrorHandler.handleError)
  }
}
