import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../login.service";
import {first} from "rxjs/operators";
import {AlertService} from "../alert.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private loginService: LoginService,
              private alertService: AlertService) {
    // redirect to home if already logged in
    if (this.loginService.currentStudentValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    /*this.loginForm = this.formBuilder.group({
      username: ['', Validators.required], ['', Validators.minLength(8)], ['', Validators.maxLength(8)],
    });*/
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]]
    })
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.loginService.login(this.loginForm.controls.username.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          //maybe show alert?
          this.alertService.error(error.error);
          this.loading = false;
        }
      )
  }

}
