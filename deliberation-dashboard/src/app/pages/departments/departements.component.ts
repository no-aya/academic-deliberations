import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import {catchError, filter, first, forkJoin, map, Observable, throwError} from "rxjs";
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
import {ElementService} from "../../services/ElementService/element.service";
import {Element} from "../model/element.model";
import {Etudiant} from "../model/etudiant";
import {EtudiantService} from "../../services/etudiantService/etudiant.service";
import {DepAr, ElementAr, FiliereAr, ModuleAr} from "../model/arbreDep.model";
import {NoteElement} from "../model/noteElement.model";
import {NoteElementService} from "../../services/noteElementService/note-element.service";

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
  depArray: DepAr[] = [];
  filieres!: Observable<Array<Filiere>>;
  modules!: Observable<Array<Module>>;
  elements! : Observable<Array<Element>>;
  etudiants!:Observable<Array<Etudiant>>;
  noteElement1!:Observable<NoteElement>;
  noteElement2!:Observable<NoteElement>;

  constructor(private fb: FormBuilder,
              private anneeUnivService : AnneeUnivService,
              private departementService: DepartementService,
              private filiereService: FiliereService,
              private moduleService: ModuleService,
              private elementService : ElementService,
              private etudiantService : EtudiantService,
              private noteElementService : NoteElementService) {
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
    // Initialiser depArray avec des valeurs par défaut
    this.depArray = [];

    // Parcourir chaque élément de depArray et les initialiser à vide
    this.depArray.forEach((dep: DepAr) => {
      dep.intitule = '';
      dep.expanded = false;
      dep.filiereChildren = [];

      dep.filiereChildren.forEach((filiere: FiliereAr) => {
        filiere.id = 0;
        filiere.intitule = '';
        filiere.expanded = false;
        filiere.moduleChildren = [];

        filiere.moduleChildren.forEach((module: ModuleAr) => {
          module.id = 0;
          module.intitule = '';
          module.expanded = false;
          module.elementChildren = [];
        });
      });
    });

    //Recuperer les departements de prof selon l'annee et semestre selectioné.
    this.departments = this.departementService.getDepartementsByProf(this.idProf, this.anneeUniv,this.semestre).pipe(
      catchError(err => {
        return throwError(err);
      }))

    if(this.departments!=null){
      this.departments.subscribe((dep: Array<Departement>) => {
        dep.forEach((d: Departement, index: number) => {
          if (!this.depArray) {
            this.depArray = [];
          }
          if (!this.depArray[index]) {
            this.depArray[index] = {
              id: 0,
              intitule: '',
              filiereChildren: []
            };
          }
          this.depArray[index].intitule=d.intitule.toString();
          this.searchFiliere(d,this.depArray[index]);
        });});

    }


  }

  searchFiliere(dep: Departement,depArray : DepAr): void {

    //Recuperer les filieres par dept, anne et semestre
    this.filieres = this.filiereService.getFiliereByProfAndDep(this.idProf, dep.id, this.anneeUniv,this.semestre).pipe(
      catchError(err => {
        return throwError(err);
      })
    )

      this.filieres.subscribe((fs: Array<Filiere>) => {
        fs.forEach((f: Filiere,index:number) => {
          let fAr: FiliereAr = {
            id: 0,
            intitule: '',
            moduleChildren: []
          };
          fAr.id = f.id; fAr.intitule=f.intitule;
          depArray.filiereChildren.push(fAr);
          this.searchModule(fAr);
        });
      });

  }

  searchModule(f : FiliereAr) {

    //afecter modules au filiere
    this.modules= this.moduleService.getModuleByProfAndFiliere(this.idProf,f.id,this.anneeUniv,this.semestre).pipe(
      catchError(err=>{
        return throwError(err);
      })
    )

      this.modules.subscribe((m: Array<Module>) => {
        m.forEach((ml: Module) => {
          let mAr: ModuleAr = {
            id: 0,
            intitule: '',
            elementChildren: [],
            etudiants:[]
          };
          mAr.id = ml.id; mAr.intitule=ml.intitule
          f.moduleChildren.push(mAr);
          this.searchElement(mAr);
        });
      });

  }

  searchElement(m:ModuleAr){
    this.elements = this.elementService.getElementByProfAndModule(this.idProf, m.id, this.anneeUniv, this.semestre).pipe(
      catchError(err => {
        return throwError(err); }))

    this.elements.subscribe((el: Array<Element>) => {
      el.forEach((e: Element) => {
        let eAr: ElementAr = {
          id: 0,
          code : '',
          titre : ''
        };
        eAr.id = e.id; eAr.code=e.code;
        eAr.titre = e.titre ? e.titre : '';
        console.log("---------"+ eAr.id);

        m.elementChildren.push(eAr);
      });
    });


}


  afficherEtu(m: ModuleAr) {
    m.expanded=!m.expanded;
   this.searchEtudiant(m);
  }
    searchEtudiant(m:ModuleAr){
    this.etudiants = this.etudiantService.getEtudiantByInscriptionAndModule(m.id).pipe(
    catchError(err => {
      return throwError(err); }))

     if(m.etudiants.length!=0){
       m.etudiants=[];
     }
       this.etudiants.subscribe((etu: Array<Etudiant>) => {
         etu.forEach((e: Etudiant) => {
           if(!m.etudiants){
             m.etudiants=[];
           }

           if (!m.etudiants.find(etudiant => etudiant.id === e.id)){
             m.etudiants.push(e);
             this.serchNoteElement(e,m,m.elementChildren[0],m.elementChildren[1]);
           }

         });
       });
     }

     serchNoteElement(e:Etudiant,m : ModuleAr, el1 : Element, el2 : Element){
       this.noteElement1 = this.noteElementService.getNoteElementByInscriptionPedagogique(e.id,m.id,el1.id);
       this.noteElement2 = this.noteElementService.getNoteElementByInscriptionPedagogique(e.id,m.id,el2.id);
       if(this.noteElement1!=null &&  this.noteElement2!=null){
        this.noteElement1.subscribe((n: NoteElement) => {
          if (!e.notesElement1) e.notesElement1=[];

           e.notesElement1[0]= n.noteSession1==null?-1:n.noteSession1;
           e.notesElement1[1]= n.noteSession2==null?-1:n.noteSession2;
            console.log("---------noteEL1"+m.intitule+" | "+  e.notesElement1[0]+" | "+e.notesElement1[1])

          },
          (error: any) => {
            console.error('Erreur lors de la récupération de la note élément :', error);
          }
        );

         this.noteElement2.subscribe((n: NoteElement) => {
             if (!e.notesElement2) e.notesElement2=[];
             e.notesElement2[0]= n.noteSession1==null?-1:n.noteSession1;
             e.notesElement2[1]= n.noteSession2==null?-1:n.noteSession2;
             console.log("---------noteEL2"+m.intitule+" | "+  e.notesElement1[0]+" | "+e.notesElement1[1])

           },
           (error: any) => {
             console.error('Erreur lors de la récupération de la note élément :', error);
           }
         );
       }
     }
}


/*
export class DepartementsComponent implements OnInit {
  idProf: number;
  deptFormGroup!: FormGroup;
  anneeUniv!: string;
  semestre!: string;

  anneesUniv! : Observable<Array<AnneeUniv>>;

  departments!: Observable<Array<Departement>>;
  filieres!: Observable<Array<Filiere>>;
  modules!: Observable<Array<Module>>;
  elements! : Observable<Array<Element>>;
  etudiants!:Observable<Array<Etudiant>>;

  constructor(private fb: FormBuilder,
              private anneeUnivService : AnneeUnivService,
              private departementService: DepartementService,
              private filiereService: FiliereService,
              private moduleService: ModuleService,
              private elementService : ElementService,
              private etudiantService : EtudiantService) {
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

   this.elements = this.elementService.getElementByProfAndModule(this.idProf, m.id, this.anneeUniv, this.semestre).pipe(
     catchError(err => {
       return throwError(err); }))
    this.elements.subscribe((e: Array<Element>) => {
      e.forEach((el: Element) => {
        console.log("------"+el.titre.toString());
      });
    });

    if(!m.elementChildren){
      m.elementChildren=[];
    }
    if(m.elementChildren.length==0) {
      this.elements.subscribe((e: Array<Element>) => {
        e.forEach((el: Element) => {
          m.elementChildren.push(el.titre.toString());
          console.log("------"+el.titre.toString());
        });
      });
    }

    this.etudiants = this.etudiantService.getEtudiantByInscriptionAndModule(m.id).pipe(
      catchError(err => {
        return throwError(err); }))


  }
}

*/
