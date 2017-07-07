import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Product} from "../../domain/product";
import {ProductsView} from "../../domain/products-view";
import {ProductService} from "../../services/product.service";
import {UserService} from "../../services/user.service";
import {CustomObservables} from "../../services/custom-observable.service";

@Component({
  selector: 'products',
  styleUrls: ['./products.component.scss'],
  templateUrl: 'products.component.html'
})
export class ProductsComponent implements OnInit {

  productsView:ProductsView = new ProductsView();

  constructor(private route: ActivatedRoute,
              private productsService:ProductService,
              private userService:UserService,
              private customObservables:CustomObservables) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.productsService.getProducts(+params["id"])
        .then(products => {
          console.log(products);
          this.productsView = products;
        });
    });
  }

  addToBasket(id:number) {
    this.userService.addProductToBasket(id)
      .then(response => this.customObservables.emitBasketChange(id));
  }
}
