import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomersComponent } from "./customers/customers.component";
import { ProductsComponent } from "./products/products.component";
import { AuthGuard } from './guard/auth.guard';
import { OrdersComponent } from './orders/orders.component';
import { OrderDetailsComponent } from './order-details/order-details.component';

const routes: Routes = [
  { path: "products", component: ProductsComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN'] } },
  { path: "customers", component: CustomersComponent, canActivate: [AuthGuard], data: { roles: ['USER'] } },
  { path: "orders", component: OrdersComponent, canActivate: [AuthGuard], data: { roles: ['USER'] } },
  {path : "order-details/:id", component : OrderDetailsComponent, canActivate : [AuthGuard], data : {roles : ['USER']}}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


}
