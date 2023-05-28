import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../model/user.model";
import {FormGroup} from "@angular/forms";
import {ComptesService} from "../../services/comptes.service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {

  users!: any;
  errorMessage!: string;
  searchFormGroup : FormGroup | undefined;

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get("http://localhost:8089/users").subscribe(data=>{
      this.users=data;
      },error=>{
        console.log(error);
      })

  }

}
