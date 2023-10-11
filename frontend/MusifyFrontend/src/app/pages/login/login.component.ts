import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginObj: {
    email:string,
    password:string
  } = {
    email : "",
    password : ""
  };
  

  constructor(private authService:AuthService){
  }
  
  handleLogin(){
    this.authService.login(this.loginObj).subscribe((result) =>{
      console.log(result);
    },
    (error) =>{
      console.log(error);
    });
  }

}
