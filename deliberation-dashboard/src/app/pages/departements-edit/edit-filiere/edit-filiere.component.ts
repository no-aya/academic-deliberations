import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {FiliereEditModel} from "../../model/filiereEdit.model";
import {FilieresAdminService} from "../../../services/filieres-admin.service";
import {DepartementEditModel} from "../../model/departementEdit.model";
import {Observable} from "rxjs";
import {DepartementsAdminService} from "../../../services/departements-admin.service";

@Component({
  selector: 'app-edit-filiere',
  templateUrl: './edit-filiere.component.html',
  styleUrls: ['./edit-filiere.component.css']
})
export class EditFiliereComponent implements OnInit {

  filiereCode: string;
  filiere!: FiliereEditModel;
  filiereFormGroup: FormGroup;
  departements : Observable<Array<DepartementEditModel>>
  constructor(
    private fb: FormBuilder,
    private filieresAdminService: FilieresAdminService,
    private departementsAdminService : DepartementsAdminService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.filiereCode = this.route.snapshot.params['code'];
    this.filiereFormGroup = this.fb.group({
      code: ['', Validators.required],
      intitule: ['', Validators.required],
      createdAt: ['', Validators.required],
      departement : ['',Validators.required],

    });
  }
  ngOnInit(): void {
    const state = history.state.filiere;
    if (state) {
      this.filiere = state;
      this.filiereFormGroup.patchValue(this.filiere);
      this.filiereFormGroup.get('departement')?.setValue('choisir département');
      this.departements = this.departementsAdminService.getDepartements();
    } else {
      this.filieresAdminService.getFiliere(this.filiereCode).subscribe({
        next: (filiere) => {
          this.filiere = filiere;
          this.filiereFormGroup.patchValue(this.filiere);
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
//departements
    }


    handleSaveFiliere() {
    let filiere: FiliereEditModel = this.filiereFormGroup.value;
    filiere.code = this.filiereFormGroup.get('departement')?.value;

    this.filieresAdminService.editFiliere(this.filiere.code, filiere).subscribe({
      next: () => {
        alert("La filière a été modifiée avec succès !");
        this.router.navigateByUrl('/filieres-admin');
      },
      error: (err) => {
        console.log(err);
      }
    });
  }






}
