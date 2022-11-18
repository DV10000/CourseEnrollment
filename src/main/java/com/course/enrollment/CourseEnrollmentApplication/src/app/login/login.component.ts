import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm, Validators, FormGroup } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { User } from '../model/user';
import { RegistrationService } from '../service/registration.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  user = new User();
  msg = '';
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;

  obSubmit(_t7: NgForm) {
    throw new Error('Method not implemented.');
  }
  
  constructor(
    private service: RegistrationService,
    private router: Router,
    private route: ActivatedRoute,
    private formbuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.loginForm = this.formbuilder.group({
      UserId: ['', [Validators.required]],
      Password: ['', [Validators.required]],
    });
    // reset login status
    this.service.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    this.service.loginUserFormRemote(this.user).pipe(first())
    .subscribe(
        data => {
        console.log('response recieved');
        this.router.navigate(['/loginsuccess']);
      },
      error => {
        //this.alertService.error(error);
        this.loading = false;
        this.msg = 'Please enter valid login details';
      }
    );
  }

  gotoregistration() {
    this.router.navigate(['/registration']);
  }

  forgotPassword() {
    this.router.navigate(['/forgotpassword']);
  }
}
