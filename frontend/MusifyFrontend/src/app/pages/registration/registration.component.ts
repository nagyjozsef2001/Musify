import { Component } from '@angular/core';
import { UserCrudService } from 'src/app/services/user-crud.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  registrationForm = new FormGroup({ //the structure of the registration form
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    passwordAgain: new FormControl('')
  });

  constructor(private userCrudService:UserCrudService){}

  register(){
    if( this.registrationForm.value.password === this.registrationForm.value.passwordAgain ){
      const userObj = {
        firstName: this.registrationForm.value.firstName ?? '',
        lastName: this.registrationForm.value.lastName ?? '',
        email: this.registrationForm.value.email ?? '',
        password: this.registrationForm.value.password ?? ''
      }

      this.userCrudService.createUser(userObj).subscribe((result: any) => {
        console.log(result);
      });
    }
    else{
      console.log("Password missmatch");
    }
    
    
  }
}
