import {Injectable} from '@angular/core';
import {Http, Headers} from "@angular/http";
import {ProductsView} from "../domain/products-view";
import {ErrorHandler} from "../utility/error-handler";
import {RequestMapping} from "../request-mapping";

@Injectable()
export class ProductService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http:Http) {
  }

  getProducts(id:number):Promise<ProductsView> {
    let url = RequestMapping.getProducts.replace('{sub_id}', id.toString());
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as ProductsView)
      .catch(ErrorHandler.handleError)
  }
}
