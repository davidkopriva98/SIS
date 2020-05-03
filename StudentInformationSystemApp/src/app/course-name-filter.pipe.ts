import { Pipe, PipeTransform } from '@angular/core';
import {Course} from "./models/course";

@Pipe({
  name: 'courseNameFilter'
})
export class CourseNameFilterPipe implements PipeTransform {

  transform(courses: Course[], text: string): Course[] {
    if (text == null || text === "") {
      return courses;
    }
    return courses.filter(c => c.name.toLowerCase().includes(text.toLowerCase()));
  }

}
