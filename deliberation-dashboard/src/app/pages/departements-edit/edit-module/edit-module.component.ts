import { Component, OnInit } from '@angular/core';
import {DepartementEditModel} from "../../model/departementEdit.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ModulesAdminService} from "../../../services/modules-admin.service";
import {ModuleEditModel} from "../../model/moduleEdit.model";

@Component({
  selector: 'app-edit-module',
  templateUrl: './edit-module.component.html',
  styleUrls: ['./edit-module.component.css']
})
export class EditModuleComponent implements OnInit {
  moduleId: String;
  module!: ModuleEditModel;
  moduleFormGroup: FormGroup;

  constructor(
    private fb: FormBuilder,
    private modulesAdminService: ModulesAdminService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.moduleId = this.route.snapshot.params['id'];
    this.moduleFormGroup = this.fb.group({
      code: ['', Validators.required],
      intitule: ['', Validators.required],
      semestre: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    const state = history.state.module;
    if (state) {
      this.module = state;
      this.moduleFormGroup.patchValue(this.module);
    } else {
      this.modulesAdminService.getModule(this.moduleId).subscribe({
        next: (module) => {
          this.module = module;
          this.moduleFormGroup.patchValue(this.module);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }




  /*handleUpdateDepartement() {
    let departement: DepartementEditModel = this.departementFormGroup.value;

    this.departementsAdminService.editDepartment(this.departementId, departement).subscribe({
      next: () => {
        this.router.navigateByUrl('/departements-admin').then(r => );
      },
      error: (err) => {
        console.log(err);
      },
    });
  }*/

  handleUpdateModule() {
    let module: ModuleEditModel = this.moduleFormGroup.value;

    this.modulesAdminService.editModule(this.moduleId, module).subscribe({
      next: () => {
        this.router.navigateByUrl('/modules-admin', {state: {updatedModule: module}}).then(
          ()=>{
            console.log(" module modifié !");

          }
        );
      },
      error: (err) => {
        console.log(err);
      },
    });
  }




}
