import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCourseComponent } from './add-course/add-course.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { LoginComponent } from './login/login.component';
import { LoginsuccessComponent } from './loginsuccess/loginsuccess.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  {path:'',component: LoginComponent},
  {path:'loginsuccess',component: LoginsuccessComponent},
  {path:'registration',component: RegistrationComponent},
  {path:'forgotpassword',component: ForgotPasswordComponent},
  {path:'login',component: LoginComponent},
  {path: 'addCourse',component: AddCourseComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
