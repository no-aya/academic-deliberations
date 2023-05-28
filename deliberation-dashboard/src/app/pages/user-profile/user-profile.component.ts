import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  username: string = "John Doe";
  nbDepartements: number = 3;
  nbFilieres: number= 5;
  nbElements : number = 20;

  constructor() { }

  ngOnInit() {
  }

}
