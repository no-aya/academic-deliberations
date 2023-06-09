import { Component, OnInit } from '@angular/core';
import {DepartementEditModel} from "../../model/departementEdit.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DepartementsAdminService} from "../../../services/departements-admin.service";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "protractor";

@Component({
  selector: 'app-edit-departement',
  templateUrl: './edit-departement.component.html',
  styleUrls: ['./edit-departement.component.css']
})
export class EditDepartementComponent implements OnInit {
   departementId  : number;
  departement! : DepartementEditModel;
  departementFormGroup :FormGroup;


  constructor(private fb: FormBuilder, private departementsAdminService: DepartementsAdminService, private router: Router, private route: ActivatedRoute) {
 this.departementId =  this.route.snapshot.params['id'];

  }

  ngOnInit(): void {
    this.departementsAdminService.getDepartment(this.departementId).subscribe({
    next:(departement) => {
        this.departement = departement;
      this.departementFormGroup = this.fb.group({
        code: this.fb.control(this.departement.code, [Validators.required]),
        intitule: this.fb.control(this.departement.intitule, [Validators.required]),
        createdAt: this.fb.control(this.departement.createdAt, [Validators.required])
      });
      },
      error : (err)=>{
      console.log(err);
      }
  });
  }

  handleUpdateDepartement() {
    // Logique de modification du département
    const departementId = this.route.snapshot.paramMap.get('id');
    let departement: DepartementEditModel = this.departementFormGroup.value;

    this.departementsAdminService.editDepartment(departementId, departement).subscribe({
      next: () => {
        this.router.navigateByUrl('/departements-admin');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

}
