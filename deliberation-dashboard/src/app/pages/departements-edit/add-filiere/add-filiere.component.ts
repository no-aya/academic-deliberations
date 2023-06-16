import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {FiliereEditModel} from "../../model/filiereEdit.model";
import {FilieresAdminService} from "../../../services/filieres-admin.service";
import {Observable} from "rxjs";
import {DepartementEditModel} from "../../model/departementEdit.model";
import {DepartementsAdminService} from "../../../services/departements-admin.service";

@Component({
  selector: 'app-add-filiere',
  templateUrl: './add-filiere.component.html',
  styleUrls: ['./add-filiere.component.css']
})
export class AddFiliereComponent implements OnInit {

  newCustomerFormGroup!: FormGroup;
  departements! : Observable<Array<DepartementEditModel>>;
  filieres: FiliereEditModel;


  constructor(
    private fb: FormBuilder,
    private filieresAdminService: FilieresAdminService,
    private departementsAdminService : DepartementsAdminService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      code: [null, [Validators.required, Validators.minLength(4)]],
      intitule: [null, [Validators.required]],
      createdAt: [null, [Validators.required]],
      departement: [null, [Validators.required]],
    });

    this.newCustomerFormGroup.get('departement')?.setValue('choisir département');

    this.departements = this.departementsAdminService.getDepartements();

  }


  handleSaveFiliere() {
    // Logique d'ajout du filiere
    let filiere: FiliereEditModel = this.newCustomerFormGroup.value;
    filiere.departementId = this.newCustomerFormGroup.get('departement')?.value;

    this.filieresAdminService.saveFiliere(filiere).subscribe({
      next: () => {
        alert("La filière a été bien enregistrée !");
        this.router.navigateByUrl('/filieres-admin');
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}

