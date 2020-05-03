import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {Student} from "./models/student";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private currentStudentSubject: BehaviorSubject<Student>;
  public currentStudent: Observable<Student>;

  constructor(private http: HttpClient) {
    this.currentStudentSubject = new BehaviorSubject<Student>(JSON.parse(localStorage.getItem('currentStudent')));
    this.currentStudent = this.currentStudentSubject.asObservable()
  }

  public get currentStudentValue(): Student {
    return this.currentStudentSubject.value;
  }

  logout() {
    localStorage.removeItem('currentStudent');
    this.currentStudentSubject.next(null);
  }

  login(studentNumber) {
    let url = "http://localhost:8080/students/"+studentNumber;

    return this.http.get<Student>(url)
      .pipe(map(student => {
        localStorage.setItem('currentStudent', JSON.stringify(student));
        this.currentStudentSubject.next(student);
        return student;
      }))
  }
}
