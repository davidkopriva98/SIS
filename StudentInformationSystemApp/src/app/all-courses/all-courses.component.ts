import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Course} from "../models/course";

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class AllCoursesComponent implements OnInit {
  courses: Course[] = [];
  searchText: string;

  constructor(private http: HttpClient) {}


  ngOnInit(): void {
    this.getAllCourses();
  }

  public updateCourses() {
    console.log(this.courses.length);
  }

  public getAllCourses() {
    let url = "http://localhost:8080/courses/all"
    this.http.get<Course[]>(url).subscribe(
      res => {
      this.courses = res;
      },
      () => {
      alert("An error has occurred")
      });
  }

}
