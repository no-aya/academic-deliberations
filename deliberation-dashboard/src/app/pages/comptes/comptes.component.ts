import { Component, OnInit } from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../model/user.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ComptesService} from "../../services/comptes.service";


@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.css']
})
export class ComptesComponent implements OnInit {

  users!: Observable<Array<User>>;
  errorMessage!: string;
  searchFormGroup : FormGroup | undefined;
  suspend: boolean;

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

  handleSuspendCompte(u: User) {
    console.log(u.id)
    let conf = confirm("Are you sure?");
    if(!conf) return;
    u.suspend=!u.suspend;
    this.compteService.saveCompte(u).subscribe({
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
