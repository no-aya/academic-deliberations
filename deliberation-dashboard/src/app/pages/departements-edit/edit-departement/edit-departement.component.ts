import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartementEditModel } from '../../model/departementEdit.model';
import { DepartementsAdminService } from '../../../services/departements-admin.service';
import {concatMap, from} from "rxjs";

@Component({
  selector: 'app-edit-departement',
  templateUrl: './edit-departement.component.html',
  styleUrls: ['./edit-departement.component.css']
})
export class EditDepartementComponent implements OnInit {
  departementId: number;
  departement!: DepartementEditModel;
  departementFormGroup: FormGroup;

  constructor(
    private fb: FormBuilder,
    private departementsAdminService: DepartementsAdminService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.departementId = this.route.snapshot.params['id'];
    this.departementFormGroup = this.fb.group({
      code: ['', Validators.required],
      intitule: ['', Validators.required],
      createdAt: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    const state = history.state.departement;
    if (state) {
      this.departement = state;
      this.departementFormGroup.patchValue(this.departement);
    } else {
      this.departementsAdminService.getDepartment(this.departementId).subscribe({
        next: (departement) => {
          this.departement = departement;
          this.departementFormGroup.patchValue(this.departement);
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

  handleUpdateDepartement() {
    let departement: DepartementEditModel = this.departementFormGroup.value;

    this.departementsAdminService.editDepartment(this.departementId, departement).subscribe({
      next: () => {
        this.router.navigateByUrl('/departements-admin', {state: {updatedDepartement: departement}}).then(
          ()=>{
            console.log(" département modifié !");

          }
        );
       },
      error: (err) => {
        console.log(err);
      },
    });
  }




}
