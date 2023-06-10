import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DepartementEditModel} from "../../model/departementEdit.model";
import {DepartementsAdminService} from "../../../services/departements-admin.service";
import {Router} from "@angular/router";
import {ModuleEditModel} from "../../model/moduleEdit.model";
import {ModulesAdminService} from "../../../services/modules-admin.service";

@Component({
  selector: 'app-add-module',
  templateUrl: './add-module.component.html',
  styleUrls: ['./add-module.component.css']
})
export class AddModuleComponent implements OnInit {
  newCustomerFormGroup!: FormGroup;
  modules: ModuleEditModel;

  constructor(
    private fb: FormBuilder,
    private modulesAdminService: ModulesAdminService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      code: [null, [Validators.required, Validators.minLength(4)]],
      intitule: [null, [Validators.required]],
      semestre: [null, [Validators.required]]
    });
  }

  handleSaveModule() {
    // Logique d'ajout du département
    let module: ModuleEditModel = this.newCustomerFormGroup.value;

    this.modulesAdminService.saveModule(module).subscribe({
      next: () => {
        alert("Le module a été bien enregistré!");
        this.router.navigateByUrl('/modules-admin');
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}
