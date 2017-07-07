import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {SignInComponent} from "./components/sign-in/sign-in.component";
import {SignUpComponent} from "./components/sign-up/sign-up.component";
import {NotAuthGuard} from "./services/guard/not-auth-guard.service";
import {ProductsComponent} from "./components/products/products.component";
import {BasketComponent} from "./components/basket/basket.component";
import {AddProductComponent} from "./components/add-product/add-product.component";

const routes:Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: MainPageComponent
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
    canActivate: [NotAuthGuard]
  },
  {
    path: 'sign-in',
    component: SignInComponent,
    canActivate: [NotAuthGuard]
  },
  {
    path: 'products/:id',
    component: ProductsComponent,
  },
  {
    path: 'user/basket',
    component: BasketComponent,
  },
  {
    path: 'admin/dashboard',
    component: AddProductComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
