import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Courses } from '../model/courses';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class HomepageService {
  constructor(private http: HttpClient) {}

  // this to be called on click of enroll course tab
  public loadEnrolledCourses(userId: string) {
    const headers = new HttpHeaders().set('userId', userId);

    return this.http.get<any[]>('http://localhost:8080/get/enrolledcourses', {
      headers: headers,
    });
  }

  // this to be called on click of course tab
  public loadAllCourses() {
    return this.http.get<any[]>('http://localhost:8080/getCourses');
  }

  // public enrolledCourse(course: Courses, userId: string): Observable<any> {
  //   const headers = new HttpHeaders().set('userId', userId);
  //   return this.http.put<any>('http://localhost:8080/enrollCourses', course, {
  //     headers: headers,
  //   });
  // }

  public enrolledCourse(course: Courses, userId: string): Observable<any> {
    const headers = new HttpHeaders().set('userId', userId);
    return this.http.put<any>('http://localhost:8080/enrollCourses', course, {
      headers: headers,
    });
  }

  public getAllCategories(){
    return this.http.get<any[]>('http://localhost:8080/getCategories');
  }
}
