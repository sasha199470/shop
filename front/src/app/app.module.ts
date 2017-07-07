import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import {AppRoutingModule} from "./app-routing.module";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {NavigationComponent} from "./components/navigation/nav.component";
import {AuthService} from "./services/auth.service";
import {UserService} from "./services/user.service";
import {AppComponent} from "./components/main-component/app.component";
import {CustomObservables} from "./services/custom-observable.service";
import {CatalogService} from "./services/catalog.service";
import {SignInComponent} from "./components/sign-in/sign-in.component";
import {SignUpComponent} from "./components/sign-up/sign-up.component";
import {AuthGuard} from "./services/guard/auth-guard.service";
import {NotAuthGuard} from "./services/guard/not-auth-guard.service";
import {ProductsComponent} from "./components/products/products.component";
import {ProductService} from "./services/product.service";
import {BasketComponent} from "./components/basket/basket.component";
import {AddProductComponent} from "./components/add-product/add-product.component";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  declarations: [
    AppComponent,
    MainPageComponent,
    NavigationComponent,
    SignInComponent,
    SignUpComponent,
    ProductsComponent,
    BasketComponent,
    AddProductComponent
  ],
  providers: [
    AuthService,
    UserService,
    CustomObservables,
    CatalogService,
    AuthGuard,
    NotAuthGuard,
    ProductService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
