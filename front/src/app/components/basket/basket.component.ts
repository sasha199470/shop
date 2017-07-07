import {Component, OnInit} from '@angular/core';
import {Product} from "../../domain/product";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.scss']
})
export class BasketComponent implements OnInit {

  products:Product[] = [];
  sum = 0;

  constructor(private userService:UserService) {
  }

  ngOnInit() {
    this.userService.getBasket()
      .then(products => {
        this.products = products;
        products.forEach(p => {
          this.sum += p.price;
        })
        this.sum *= 10;
      });
  }
}
