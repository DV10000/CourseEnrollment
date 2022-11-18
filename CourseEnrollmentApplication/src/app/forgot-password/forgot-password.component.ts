import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../service/registration.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  user = new User();
  msg = '';

  reactiveform!: FormGroup;

  constructor(
    private service: RegistrationService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.reactiveform = this.formBuilder.group(
      {
        userId: new FormControl(null, Validators.required),
        newPassword: new FormControl(null, [
          Validators.required,
          Validators.minLength(6),
        ]),
        cPassword: new FormControl('', Validators.required),
      },
      {
        validators: this.MustMatch('newPassword', 'cPassword'),
      }
    );
  }

  get f() {
    return this.reactiveform.controls;
  }

  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors['MustMatch']) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ MustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  onSubmit() {}

  ngOnInit(): void {}

  updatePassword() {
    this.user.userLoginId = this.reactiveform.value.userId;
    this.user.password = this.reactiveform.value.newPassword;
    this.service.forgotPassword(this.user).subscribe(
      (data) => {
        console.log('password updated');
        this.msg = 'Password Updated';
      },
      (error) => {
        console.log('Exception Ocurred');
        this.msg = "New Password and confirm password didn't matched";
      }
    );
    this.router.navigate(['/login']);
  }
}
