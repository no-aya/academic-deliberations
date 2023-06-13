import { Component, OnInit } from '@angular/core';
import {catchError, map, Observable, throwError} from "rxjs";
import {DepartementEditModel} from "../../model/departementEdit.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DepartementsEditService} from "../../../services/departements-edit.service";
import {AddEditDepartComponent} from "../add-edit-depart/add-edit-depart.component";
import {DepartementsAdminService} from "../../../services/departements-admin.service";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-departements-admin',
  templateUrl: './departements-admin.component.html',
  styleUrls: ['./departements-admin.component.css']
})
export class DepartementsAdminComponent implements OnInit {

departement : Observable<DepartementEditModel>;
  departements!: Observable<Array<DepartementEditModel>>;
  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;

  constructor(private departementsAdminService: DepartementsAdminService, private fb: FormBuilder, private router : Router) {
  }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control("")
    });
    this.departements = this.departementsAdminService.getDepartements().pipe(
      catchError(err => {
          this.errorMessage = err.message;
          return throwError(err);
        }
      ));
  }

  handleSearchDepartements() {
    let kw = this.searchFormGroup?.value.keyword;
    this.departements = this.departementsAdminService.searchDepartements(kw).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }



  handleDeleteDepartement(d: DepartementEditModel) {
    let conf = confirm("Are you sure?");
    if (!conf) return;
    this.departementsAdminService.deleteDepartement(d.code).subscribe({
      next: (resp) => {
        this.departements = this.departements.pipe(
          map(data => {
            let index = data.indexOf(d);
            data.slice(index, 1)
            return data;
          })
        );
      },
      error: err => {
        console.log(err);
      }
    })
  }





  handleEditDepartement(departement: DepartementEditModel) {
    this.router.navigateByUrl("/edit-departement/" + departement.id, { state: { departement } })
      .then(() => {
        // Actions à effectuer après la navigation
      })
      .catch((error) => {
        console.log(error);
      });
  }


}
