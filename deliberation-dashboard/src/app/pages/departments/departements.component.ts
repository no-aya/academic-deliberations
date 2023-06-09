import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import {catchError, first, Observable, throwError} from "rxjs";
import { find } from 'rxjs/operators';
import {DepartementService} from "../../services/DepartementService/departement.service";
import {Departement} from "../model/departement.model";
import {Filiere} from "../model/filiere.model";
import {FiliereService} from "../../services/FiliereService/filiere.service";
import {Module} from "../model/module.model";
import {ModuleService} from "../../services/ModuleService/module.service";
import { FormGroup, FormBuilder } from '@angular/forms';
import {AnneeUnivService} from "../../services/anneeUnivService/annee-univ.service";
import {AnneeUniv} from "../model/anneeUniv";

@Component({
  selector: 'app-departements',
  templateUrl: './departements.component.html',
  styleUrls: ['./departements.component.scss'],
})
export class DepartementsComponent implements OnInit {
  idProf: number;
  deptFormGroup!: FormGroup;
  anneeUniv!: string;
  semestre!: string;

  anneesUniv! : Observable<Array<AnneeUniv>>;

  departments!: Observable<Array<Departement>>;
  filieres!: Observable<Array<Filiere>>;
  modules!: Observable<Array<Module>>;

  constructor(private fb: FormBuilder,
              private anneeUnivService : AnneeUnivService,
              private departementService: DepartementService,
              private filiereService: FiliereService,
              private moduleService: ModuleService) {
  }

  ngOnInit() {
    this.idProf = 1;
    this.deptFormGroup = this.fb.group({
      anneeUniv: this.fb.control('22-23'), // Set the initial value here
      semestre: this.fb.control('Semestre1')
      })

    //Recuperer les annees universitaire :
    this.anneesUniv= this.anneeUnivService.getAllAnneuniv().pipe(
      catchError(err=>{
        return throwError(err);
      }));
  }

  searchDept() {
     this.anneeUniv=this.deptFormGroup.value.anneeUniv;
     this.semestre=this.deptFormGroup.value.semestre;

    //Recuperer les departements de prof selon l'annee et semestre selectioné.
    this.departments = this.departementService.getDepartementsByProf(this.idProf, this.anneeUniv,this.semestre).pipe(
      catchError(err => {
        return throwError(err);
      }))
  }


  searchFiliere(dep: Departement): void {
    dep.expanded = !dep.expanded;

      //Recuperer les filieres par dept, anne et semestre
      this.filieres = this.filiereService.getFiliereByProfAndDep(this.idProf, dep.id, this.anneeUniv,this.semestre).pipe(
        catchError(err => {
          return throwError(err);
        })
      )
      if (!dep.filiereChildren) {
        dep.filiereChildren = []; // Initialise filiereChildren comme un tableau vide si c'est undefined
      }

      if(dep.filiereChildren.length==0) {
        this.filieres.subscribe((fs: Array<Filiere>) => {
          fs.forEach((f: Filiere) => {
            dep.filiereChildren.push(f.intitule.toString());
          });
        });
      }

    }


  searchModule(dep: Departement,f :Filiere) {
    f.expanded = !f.expanded;

    //Recuperer les filieres par dept, anne et semestre
    this.modules= this.moduleService.getModuleByProfAndFiliere(this.idProf,f.id,this.anneeUniv,this.semestre).pipe(
      catchError(err=>{
        return throwError(err);
      })
    )

    if (!f.moduleChildren) {
      f.moduleChildren = []; // Initialise filiereChildren comme un tableau vide si c'est undefined
    }

    if(f.moduleChildren.length==0) {
      this.modules.subscribe((m: Array<Module>) => {
        m.forEach((ml: Module) => {
          f.moduleChildren.push(ml.intitule.toString());
        });
      });
    }

}

  searchStudent(dep: Departement, f: Filiere, m: Module) {
    m.expanded = !m.expanded;

  }
}

