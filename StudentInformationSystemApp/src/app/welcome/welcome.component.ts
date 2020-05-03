import { Component, OnInit } from '@angular/core';
import {Student} from "../models/student";
import {Router} from "@angular/router";
import {LoginService} from "../login.service";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  currentStudent: Student;

  constructor(private loginService: LoginService) {
    this.loginService.currentStudent.subscribe(x => this.currentStudent = x);
  }

  public getStudent(): Student {
    return this.currentStudent;
  }

  ngOnInit(): void {
  }
}
