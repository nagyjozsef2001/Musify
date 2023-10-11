import { Component, OnInit } from '@angular/core';
import { UserCrudService } from 'src/app/services/user-crud.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit{
  userList:any[]=[];

  constructor(private userCrudService:UserCrudService){}

  ngOnInit(): void {
    this.loadUsers();
    console.log(this.userList);
  }

  loadUsers(){
    this.userCrudService.getAllUser().subscribe((result: any) =>{
      this.userList = result;
      console.log(result);
    })
  }

}
