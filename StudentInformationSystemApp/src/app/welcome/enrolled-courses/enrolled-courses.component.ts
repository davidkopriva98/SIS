import {Component, OnInit} from '@angular/core';
import {Course} from "../../models/course";
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../../login.service";
import {Student} from "../../models/student";
import {AlertService} from "../../alert.service";

@Component({
  selector: 'app-enrolled-courses',
  templateUrl: './enrolled-courses.component.html',
  styleUrls: ['./enrolled-courses.component.css']
})
export class EnrolledCoursesComponent implements OnInit {
  available: Course[];
  selected: Course[];
  private addCourses: Course[];
  private removeCourses: Course[];
  currentStudent: Student;
  private course: Course;

  constructor(private http: HttpClient, private loginService: LoginService, private alertService: AlertService) {
    this.loginService.currentStudent.subscribe(x => this.currentStudent = x);
  }

  ngOnInit(): void {
    this.getCourses();
    this.addCourses = [];
    this.removeCourses = [];
    //this.getAllCourses();
  }

  private getAllCourses(): void {
    let url = "http://localhost:8080/courses/all"
    this.http.get<Course[]>(url).subscribe(
      res => {
        this.available = res;
        this.removeFromAll();
      },
      () => {
        alert("An error has occurred")
      });
  }

  private getCourses(): void {
    let url = "http://localhost:8080/students/" + this.currentStudent.id + "/courses";
    this.http.get<Course[]>(url).subscribe(
      res => {
        this.selected = res;
        //get all courses
        let url = "http://localhost:8080/courses/all"
        this.http.get<Course[]>(url).subscribe(
          res => {
            this.available = res;
            this.removeFromAll();
          },
          () => {
            this.alertService.error("An error has occurred");
          });
      },
      () => {
        this.alertService.error("An error has occurred");
      });
  }

  private removeFromAll() {
    for (let i = 0; i < this.selected.length; i++) {
      let index = this.available.findIndex(c => c.name === this.selected[i].name && c.id === this.selected[i].id);
      if (index != -1) {
        this.available.splice(index, 1);
      }
    }
  }

  resetCourses() {
    this.getCourses();
    this.getAllCourses();
    this.alertService.clear();
  }

  saveChanges() {
    if (this.addCourses.length != 0) {
      let urlAdd = "http://localhost:8080/students/" + this.currentStudent.id + "/enrollMultiple/";
      for (let i = 0; i < this.addCourses.length; i++) {
        urlAdd += this.addCourses[i].id;
        if (i < this.addCourses.length - 1) {
          urlAdd += "&";
        }
      }
      this.http.post(urlAdd, null).subscribe(() => {
        this.alertService.success("Changes saved successfully!");
      }, error => {
        this.alertService.error("An error has occurred");
      });
    }
    if (this.removeCourses.length != 0) {
      let urlRem = "http://localhost:8080/students/" + this.currentStudent.id + "/disenrollMultiple/";
      for (let i = 0; i < this.removeCourses.length; i++) {
        urlRem += this.removeCourses[i].id;
        if (i < this.removeCourses.length - 1) {
          urlRem += "&";
        }
      }
      this.http.post(urlRem, null).subscribe(() => {
        this.alertService.success("Changes saved successfully!");
      }, error => {
        this.alertService.error("An error has occurred");
      });
    }
  }

  enrollTo(all: Course[]) {
    this.addCourses = this.addCourses.concat(all);
  }

  disenrollTo(all: Course[]) {
    this.removeCourses = this.removeCourses.concat(all);
  }
}
