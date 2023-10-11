import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  login(loginObj:any):Observable<any>{
    return this.http.post("http://localhost:3000/login", loginObj).pipe(map((res: any) =>{
      localStorage.setItem('jwtToken', res.access_token);
      return res;
    }));
  }

  getOptions(){
    var headers_object = new HttpHeaders({
      'Content-Type': 'application/json',
       'Authorization': "Bearer "+ localStorage.getItem('jwtToken')
    });

    const httpOptions = {
      headers: headers_object
    };

    return httpOptions;
  }
}
