import {Course} from "./course";

export interface Student {
  id:BigInteger,
  firstName:string,
  lastName:string,
  enrolledCourses: Course[]
}
