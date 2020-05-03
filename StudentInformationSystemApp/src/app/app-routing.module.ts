import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from "./welcome/welcome.component";
import {AllCoursesComponent} from "./all-courses/all-courses.component";
import {ErrorComponent} from "./error/error.component";
import {LoginComponent} from "./login/login.component";


const routes: Routes = [
  {
    path:"start",
    component: WelcomeComponent
  },
  {
    path:"courses",
    component: AllCoursesComponent
  },
  {
    path:"login",
    component: LoginComponent
  },
  {
    path:"",
    component: WelcomeComponent,
    pathMatch:"full"
  },
  {
    path:"**",
    component: ErrorComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
