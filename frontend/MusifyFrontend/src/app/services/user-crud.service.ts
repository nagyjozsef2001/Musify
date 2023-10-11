import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserCrudService {

  constructor(private http:HttpClient, private authService:AuthService) { }

  createUser(userObj: object):Observable<any>{
    return this.http.post<any>(`http://localhost:3000/user/registration`, userObj, this.authService.getOptions());
  }

  deleteUser(id:number){
    return this.http.delete<any>(`http://localhost:3000/user/delete/${id}`, this.authService.getOptions());
  }

  promoteUser(id: number){
    return this.http.put<any>(`http://localhost:3000/user/promote/${id}`, this.authService.getOptions());
  }

  modifyUser(id:number,obj:any):Observable<any>{
    return this.http.put<any>(`http://localhost:3000/user/update/${id}`, obj, this.authService.getOptions());
  }

  inactivateUser(id:number):Observable<any>{
    return this.http.put<any>(`http://localhost:3000/user/inactivate/${id}`, this.authService.getOptions());
  }

  getUser(id:number):Observable<any>{
    return this.http.get<any>(`http://localhost:3000/user/get/${id}`, this.authService.getOptions());
  }

  getAllUser():Observable<any[]>{
    return this.http.get<any[]>("http://localhost:3000/user/get", this.authService.getOptions());
  }
  
}
