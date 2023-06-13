import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DepartementEditModel} from "../../model/departementEdit.model";
import {DepartementsAdminService} from "../../../services/departements-admin.service";
import {Router} from "@angular/router";
import {ModulesAdminService} from "../../../services/modules-admin.service";
import {ModuleEditModel} from "../../model/moduleEdit.model";

@Component({
  selector: 'app-add-module',
  templateUrl: './add-module.component.html',
  styleUrls: ['./add-module.component.css']
})
export class AddModuleComponent implements OnInit {
  newCustomerFormGroup!: FormGroup;
  libelles: string[] = ['S1', 'S2', 'S3', 'S4', 'S5'];
  // semestreList: Semestre["libelle"];
  // semestreLists : string[] = [];
 // libelleSemestre = []

  constructor(
    private fb: FormBuilder,
    private modulesAdminService: ModulesAdminService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
     this.newCustomerFormGroup = this.fb.group({
       code: [null, [Validators.required, Validators.minLength(4)]],
       intitule: [null, [Validators.required]],
       semestre: [null, [Validators.required]]
     });

     this.modulesAdminService.getModules().subscribe(
       (modules: ModuleEditModel[]) => {
      //   const uniqueSemestres = Array.from(new Set(modules.map(module => module.semestre.libelle)));
         //this.semestreLists = uniqueSemestres;
       },
       error => {
         console.log(error);
       }
     );
  }


  handleSaveModule() {
    /*if (this.newCustomerFormGroup.invalid) {
      // Validation du formulaire
      return;
    }

    const selectedSemestre = this.newCustomerFormGroup.value.semestre;

    const module: ModuleEditModel = {
      id: 0, // Vous pouvez laisser cette valeur à 0 si elle sera générée par la base de données
      code: this.newCustomerFormGroup.value.code,
      intitule: this.newCustomerFormGroup.value.intitule,
      semestre: {
        id: 0, // Assurez-vous d'ajouter l'ID du semestre ici
        code: "", // Remplacez par les valeurs appropriées
        libelle: selectedSemestre
      }
    };

    this.modulesAdminService.saveModule(module).subscribe({
      next: () => {
        alert("Le module a été bien enregistré!");
        this.router.navigateByUrl('/modules-admin');
      },
      error: (err) => {
        console.log(err);
      }
    });*/

  }
}
