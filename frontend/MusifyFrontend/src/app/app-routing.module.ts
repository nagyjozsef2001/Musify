import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { UsersComponent } from './pages/users/users.component';
import { RegistrationComponent } from './pages/registration/registration.component';

const routes: Routes = [
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"user",
    component:UsersComponent
  },
  {
    path:"register",
    component:RegistrationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
