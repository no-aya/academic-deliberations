import { Component, OnInit } from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../model/user.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ComptesService} from "../../services/comptes.service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {

  users!: Observable<Array<User>>;
  errorMessage!: string;
  searchFormGroup : FormGroup | undefined;

  constructor(private compteService: ComptesService,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")

    });
    this.users=this.compteService.getComptes().pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      }
    ));
  }

  handleSearchComptes() {
    let kw=this.searchFormGroup?.value.keyword;
    this.users=this.compteService.searchComptes(kw).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }


  handleDeleteCompte(u: User) {
    let conf = confirm("Are you sure?");
    if(!conf) return;
    this.compteService.deleteCompte(u.id).subscribe({
      next : (resp) => {
        this.users=this.users.pipe(
          catchError(err => {
            this.errorMessage=err.message;
            return throwError(err);
          })
        );
      },
      error : err => {
        console.log(err);
      }
    })

  }
}
