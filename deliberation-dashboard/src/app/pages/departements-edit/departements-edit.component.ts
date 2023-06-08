import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {DepartementsEditService} from "../../services/departements-edit.service";
import {DepartementEditModel} from "../model/departementEdit.model";

@Component({
  selector: 'app-departements-edit',
  templateUrl: './departements-edit.component.html',
  styleUrls: ['./departements-edit.component.css']
})

export class DepartementsEditComponent implements OnInit {
  departements! : Observable<Array<DepartementEditModel>>;
  errorMessage!: string;
  searchFormGroup : FormGroup | undefined;

  constructor(private departementsEditService : DepartementsEditService, private fb : FormBuilder) { }

  ngOnInit(): void {
    this.searchFormGroup=this.fb.group({
      keyword : this.fb.control("")
    });
    this.departements=this.departementsEditService.getDepartements().pipe(
      catchError(err => {
          this.errorMessage=err.message;
          return throwError(err);
        }
      ));
  }

  handleSearchDepartements() {
    let kw=this.searchFormGroup?.value.keyword;
    this.departements=this.departementsEditService.searchDepartements(kw).pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      })
    );
  }

  handleDeleteDepartement(d: DepartementEditModel) {
    let conf = confirm("Are you sure?");
    if(!conf) return;
    this.departementsEditService.deleteDepartement(d.code).subscribe({
      next : (resp) => {
        this.departements=this.departements.pipe(
          map(data=>{
            let index=data.indexOf(d);
            data.slice(index,1)
            return data;
          })
        );
      },
      error : err => {
        console.log(err);
      }
    })
  }

  /*handleCustomerAccounts(departement: DepartementEditModel) {
    this.router.navigateByUrl("/customer-accounts/"+departement.id,{state :departement});
  }*/
  handleUpdateDepartement(d: DepartementEditModel) {
    
  }
}

