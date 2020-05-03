import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { NavComponent } from './nav/nav.component';
import {ErrorComponent} from "./error/error.component";
import {AllCoursesComponent} from "./all-courses/all-courses.component";
import {ButtonModule, CardModule, CarouselModule, InputTextModule, PaginatorModule, PickListModule} from "primeng";
import {HttpClientModule} from "@angular/common/http";
import { CourseNameFilterPipe } from './course-name-filter.pipe';
import { LoginComponent } from './login/login.component';
import {ReactiveFormsModule} from "@angular/forms";
import { AlertsComponent } from './alerts/alerts.component';
import { EnrolledCoursesComponent } from './welcome/enrolled-courses/enrolled-courses.component';


@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    NavComponent,
    AllCoursesComponent,
    ErrorComponent,
    CourseNameFilterPipe,
    LoginComponent,
    AlertsComponent,
    EnrolledCoursesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CardModule,
    InputTextModule,
    PaginatorModule,
    ButtonModule,
    CarouselModule,
    HttpClientModule,
    ReactiveFormsModule,
    PickListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
