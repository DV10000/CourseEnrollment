import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, NgForm, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { RegistrationService } from '../service/registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  user = new User();
  msg = '';
  @Input()
  answer!: boolean ;

  registerForm !: FormGroup;
  constructor(private service: RegistrationService, private router: Router) {
  }

  ngOnInit(): void {
    this.futureDateDisable();
  }

  registeruser() {
    this.user.isAdmin = this.answer;
    this.service.registerUserFormRemote(this.user).subscribe(
      (data) => {
        console.log('response recieved');
        this.msg = 'Resgistration Successfull';
      },
      (error) => {
        console.log('Exception Ocurred');
        this.msg = 'User already Exist';
      }
    );
  }

  backToLogin() {
    this.router.navigate(['/login']);
  }

  resetScreen() {
    this.msg = '';
  }

  maxDate: any;

  futureDateDisable() {
    var date = new Date();
    var todayDate: any = date.getDate();
    var month: any = date.getMonth();
    var year: any = date.getFullYear();
    if (todayDate < 10) {
      todayDate = '0' + todayDate;
    }

    if (month < 10) {
      month = '0' + month;
    }

    this.maxDate = year + '-' + month + '-' + todayDate;
    console.log(date);
  }
}
