import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {UserService} from "../../services/user.service";
import {CustomObservables} from "../../services/custom-observable.service";
import {CatalogService} from "../../services/catalog.service";
import {Category} from "../../domain/category";


@Component({
  selector: 'navigation',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})

export class NavigationComponent implements OnInit {

  categories:Category[];
  numberOfBasket = 0;
  isLoggedIn:boolean;
  isHover:boolean[] = [];
  isAdmin = false;

  constructor(private authService:AuthService,
              private userService:UserService,
              private catalogService:CatalogService,
              private customObservables:CustomObservables,
              private router:Router) {
    this.customObservables.changeAuthEmitted.subscribe(() => this.updateUserAuth());
    this.customObservables.changeBasketEmitted.subscribe(() => this.updateBasket());
  }

  ngOnInit():void {
    this.updateUserAuth();
    this.updateBasket()
  }

  updateUserAuth() {
    this.userService.getCurrentUser()
      .then(user => {
        this.isLoggedIn = true;
        user.roles.forEach(role => {
          if (role.name = 'admin') this.isAdmin = true;
        });
      })
      .catch(e => this.isLoggedIn = false)
  }

  updateBasket() {
    this.userService.getBasket()
      .then(products => this.numberOfBasket = products.length)
  }

  getCatalog() {
    this.catalogService.getCatalogs()
      .then(categories => {
        console.log(categories);
        this.categories = categories;
      });
  }

  logOut():void {
    this.authService.logOut()
      .then(() => {
        this.isLoggedIn = false;
      });
  }
}
