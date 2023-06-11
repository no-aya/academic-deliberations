import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProfesseurService} from "../../services/professeur.service";
import {EtudiantService} from "../../services/etudiant.service";

@Component({
  selector: 'app-prof-etud',
  templateUrl: './prof-etud.component.html',
  styleUrls: ['./prof-etud.component.css']
})
export class ProfEtudComponent implements OnInit {
  newProfesseurFormGroup!: FormGroup;
  newEtudiantFormGroup! : FormGroup;

  constructor(private profService : ProfesseurService, private etudiantService : EtudiantService, private fb:FormBuilder) { }

  ngOnInit(): void {
    this.newProfesseurFormGroup=this.fb.group({
      nom : this.fb.control(null),
      prenom : this.fb.control(null),
      cin : this.fb.control(null),
      email : this.fb.control(null),
    });
    this.newEtudiantFormGroup=this.fb.group({
      nom : this.fb.control(null),
      prenom : this.fb.control(null),
      apogee : this.fb.control(null),
      email : this.fb.control(null)
    });


    }


  handleSaveProfesseur() {
    let prof = this.newProfesseurFormGroup.value;
    this.profService.saveProfesseur(prof).subscribe({
      next : (resp) => {
        alert("Professeur ajouté avec succès");
        this.newProfesseurFormGroup.reset();
      },
      error : err => {
        console.log(err);
      }
    });
  }

  handleSaveEtudiant() {
    let etud = this.newEtudiantFormGroup.value;
    this.etudiantService.saveEtudiant(etud).subscribe({
      next : (resp) => {
        alert("Etudiant ajouté avec succès");
        this.newEtudiantFormGroup.reset();
      },
      error : err => {
        console.log(err);
      }
    });
  }
}
