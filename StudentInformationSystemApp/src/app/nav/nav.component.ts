import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Student} from "../models/student";
import {LoginService} from "../login.service";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  currentStudent: Student;

  constructor(private router: Router,
              private loginService: LoginService) {
    this.loginService.currentStudent.subscribe(x => this.currentStudent = x);
  }

  ngOnInit(): void {
  }

  logout() {
    this.loginService.logout();
    this.router.navigate(['']);
  }

}
