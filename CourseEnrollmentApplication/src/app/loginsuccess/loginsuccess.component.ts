import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomepageService } from '../service/homepage.service';
import { Courses } from '../model/courses';
import { AuthService } from '../service/auth.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { User } from '../model/user';
import { LoginComponent } from '../login/login.component';
import { RegistrationService } from '../service/registration.service';

@Component({
  selector: 'app-loginsuccess',
  templateUrl: './loginsuccess.component.html',
  styleUrls: ['./loginsuccess.component.css'],
})
export class LoginsuccessComponent implements OnInit {
  course = new Courses();
  user = new User();
  msg = '';
  isEnrolledFlag: false | undefined;
  allCourses: any[] = [];
  allCategory: any[] = [];
  row!: Courses;
  selectedRow!: Courses;
  loginSuccess: FormGroup | undefined;
  userForm!: FormGroup;
  isAdmin!: boolean;
  name = '';
  admin: any;
  currentUser: User;
  isAdminFlag!: boolean;
  buttonDisableFlag!: boolean;
  buttonEnabledFlag!: boolean;
  selectedStatus = '';
  breakMe = false;

  constructor(
    private router: Router,
    private service: HomepageService,
    private auth: AuthService,
    private formBuilder: FormBuilder
  ) {
    this.loginSuccess = this.formBuilder.group({
      course_id: new FormControl('', Validators.required),
      course_name: new FormControl('', Validators.required),
      course_author: new FormControl('', Validators.required),
      course_category: new FormControl('', Validators.required),
      course_creationDate: new FormControl('', Validators.required),
      course_authorExp: new FormControl('', Validators.required),
      status: new FormControl('', Validators.required),
    });

    this.currentUser = JSON.parse(localStorage.getItem('currentUser')!);
  }

  ngOnInit() {
    this.reload();
    this.getCategories();
  }

  userFormState(): void {
    this.userForm = this.formBuilder.group({
      password: new FormControl('', Validators.required),
      userLoginId: new FormControl('', Validators.required),
      emailId: new FormControl('', Validators.required),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      dob: new FormControl('', Validators.required),
      isAdmin: new FormControl('', Validators.required),
    });
  }

  adminStatus(): boolean {
    this.isAdmin = this.currentUser.isAdmin;
    if (this.isAdmin) {
      return (this.isAdminFlag = true);
    } else {
      return (this.isAdminFlag = false);
    }
  }

  enrollButton(){
    this.allCourses.every((element) => {
        if (element.status === 'Enrolled') {
          return this.buttonDisableFlag = true;
      } else {
          return this.buttonDisableFlag = false;
      } 
    });
  }

  reload() {
    this.service.loadAllCourses().subscribe((data) => {
      console.log(data);
      this.allCourses = data;
      this.enrollButton();
      // this.allCourses.forEach((element) => {
      //   //loadEnrolledCourses
      //   // check thi reterived course id with existing course ids and if courseid is matched with existing courses then disable the button
    });

    
  }

  id: any = 'enrolledCourse';
  tabChange(ids: any) {
    this.id = ids;
  }

  getCategories() {
    this.service.getAllCategories().subscribe((data) => {
      console.log(data);
      this.allCategory = data;
    });
  }

  enrollCourse(selecteddata: any) {
    this.row = selecteddata;
    this.row.status = 'Enrolled';
    this.service
      .enrolledCourse(this.row, this.currentUser.userLoginId)
      .subscribe(
        (data) => {
          console.log(this.row);
          console.log('response recieved');
          this.msg = 'Courses displayed';
        },
        (error) => {
          console.log('Exception Ocurred');
          this.msg = 'Course not present';
        }
      );
  }

  logout() {
    this.auth.logout();
    this.router.navigateByUrl('/login');
  }
}
