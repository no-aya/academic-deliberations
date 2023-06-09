import { Component, OnInit } from '@angular/core';
import {catchError, map, Observable, throwError} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {ModuleEditModel, Semestre} from "../../model/moduleEdit.model";
import {ModulesAdminService} from "../../../services/modules-admin.service";

@Component({
  selector: 'app-modules-admin',
  templateUrl: './modules-admin.component.html',
  styleUrls: ['./modules-admin.component.css']
})
export class ModulesAdminComponent implements OnInit {

  modules!: Observable<Array<ModuleEditModel>>;
  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;
  selectedSemestre: { [key: number]: Semestre } = {};
  constructor(
    private modulesAdminService: ModulesAdminService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control('')
    });

    this.modules = this.modulesAdminService.getModules().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleSearchModules() {
    let kw = this.searchFormGroup?.value.keyword;
    this.modules = this.modulesAdminService.searchModules(kw).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleDeleteModule(d: ModuleEditModel) {
    let conf = confirm('Are you sure?');
    if (!conf) return;

    this.modulesAdminService.deleteModule(d.code).subscribe({
      next: (resp) => {
        this.modules = this.modules.pipe(
          map(data => {
            let index = data.indexOf(d);
            data.slice(index, 1);
            return data;
          })
        );
      },
      error: err => {
        console.log(err);
      }
    });
  }

  handleEditModule(module: ModuleEditModel) {
    this.router.navigateByUrl('/edit-module/' + module.id, { state: module });
  }
}

