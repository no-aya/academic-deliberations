import { Component, OnInit } from '@angular/core';
import {catchError, map, Observable, throwError} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {FilieresAdminService} from "../../../services/filieres-admin.service";
import {FiliereEditModel} from "../../model/filiereEdit.model";


@Component({
  selector: 'app-filieres-admin',
  templateUrl: './filieres-admin.component.html',
  styleUrls: ['./filieres-admin.component.css']
})
export class FilieresAdminComponent implements OnInit {

  filiere : Observable<FiliereEditModel>;
  filieres!: Observable<Array<FiliereEditModel>>;
  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;

  constructor(private filieresAdminService: FilieresAdminService, private fb: FormBuilder, private router : Router) {
  }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control("")
    });
    this.filieres = this.filieresAdminService.getFilieres().pipe(
      catchError(err => {
          this.errorMessage = err.message;
          return throwError(err);
        }
      ));
  }

  handleSearchFilieres() {
    let kw = this.searchFormGroup?.value.keyword;
    this.filieres = this.filieresAdminService.searchFilieres(kw).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }


  handleDeleteFiliere(d: FiliereEditModel) {
    let conf = confirm("Are you sure?");
    if (!conf) return;
    this.filieresAdminService.deleteFiliere(d.code).subscribe({
      next: (resp) => {
        this.filieres = this.filieres.pipe(
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





  handleEditFiliere(filiere: FiliereEditModel) {
    this.router.navigateByUrl("/edit-filiere/" + filiere.code, { state: { filiere } })
      .then(() => {
        // Actions à effectuer après la navigation
      })
      .catch((error) => {
        console.log(error);
      });
  }
}
