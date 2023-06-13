import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DepartementsAdminService} from "../../../services/departements-admin.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DepartementEditModel} from "../../model/departementEdit.model";

@Component({
  selector: 'app-add-edit-depart',
  templateUrl: './add-edit-depart.component.html',
  styleUrls: ['./add-edit-depart.component.css']
})
export class AddEditDepartComponent implements OnInit {

  newCustomerFormGroup!: FormGroup;
  departement: DepartementEditModel;
  editMode = false;

  constructor(
    private fb: FormBuilder,
    private departementsAdminService: DepartementsAdminService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.newCustomerFormGroup = this.fb.group({
      code: [null, [Validators.required, Validators.minLength(4)]],
      intitule: [null, [Validators.required]],
      createdAt: [null, [Validators.required]]
    });
  }

  handleSaveDepartement() {
      // Logique d'ajout du département
      let departement: DepartementEditModel = this.newCustomerFormGroup.value;

      this.departementsAdminService.saveDepartement(departement).subscribe({
        next: () => {
          alert("Le département a été bien enregistré!");
          this.router.navigateByUrl('/departements-admin');
        },
        error: (err) => {
          console.log(err);
        }
      });
    }


}

