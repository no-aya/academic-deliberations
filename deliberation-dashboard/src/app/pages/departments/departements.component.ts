import { Component, OnInit } from '@angular/core';
import {catchError, filter, first, forkJoin, map, Observable, of, throwError} from "rxjs";
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
import {NoteModuleService} from "../../services/noteModuleService/note-module.service";
import {NoteModule} from "../model/noteModule.model";
import {coerceStringArray} from "@angular/cdk/coercion";

@Component({
  selector: 'app-departements',
  templateUrl: './departements.component.html',
  styleUrls: ['./departements.component.css'],
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
  noteModule!:Observable<NoteModule>;
  idnM:number;
  n:string;
  statut:string;

  isEditing: boolean = false;
  editingCell: number = -1;
  originalValue: number = 0;

  constructor(private fb: FormBuilder,
              private anneeUnivService : AnneeUnivService,
              private departementService: DepartementService,
              private filiereService: FiliereService,
              private moduleService: ModuleService,
              private elementService : ElementService,
              private etudiantService : EtudiantService,
              private noteElementService : NoteElementService,
              private  noteModuleService : NoteModuleService) {
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
          module.etudiants = [];


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
            etudiants: []

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
           e.editMode = [];
           e.editMode[0] = false; // Colonne 1
           e.editMode[1] = false; // Colonne 2
           e.editMode[2] = false; // Colonne 2
           e.editMode[3] = false; // Colonne 2
           e.notesElement1=[];
          e.notesElement2=[];
          e.idNoteModule=0;

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
       this.idnM=m.id;
       console.log("oooooooooook"+this.idnM);
       if(this.noteElement1!=null &&  this.noteElement2!=null){
        this.noteElement1.subscribe((n: NoteElement) => {

            if (!e.notesElement1 ) {
              e.notesElement1 = [];
            }
            if(!e.idNotesElement1){
              e.idNotesElement1=[];
            }

           e.idNotesElement1[0] = n.id;
           e.notesElement1[0]= n.noteSession1==null?-1:n.noteSession1;
           e.idNotesElement1[1]  = n.id;
           e.notesElement1[1]= n.noteSession2==null?-1:n.noteSession2;
          //  console.log("---------"+  e.idNotesElement1[0]+" | "+ e.idNotesElement1[1]);

          },
          (error: any) => {
          //  console.error('Erreur lors de la récupération de la note élément :', error);
          }
        );

         this.noteElement2.subscribe((n: NoteElement) => {

             if (!e.notesElement2) {
               e.notesElement2 = [];
             }
             if(!e.idNoteElement2){
               e.idNoteElement2=[];
             }

            // console.log("---------noteEL2"+m.intitule+" | "+  e.notesElement1[0].noteSession1+" | "+e.notesElement1[1].noteSession2)

             e.idNoteElement2[0]  = n.id;
             e.notesElement2[0]= n.noteSession1==null?-1:n.noteSession1;
             e.idNoteElement2[1]  = n.id;
           //  console.log("---------"+n.noteSession1+"|"+n.noteSession2);
             e.notesElement2[1]= n.noteSession2==null?-1:n.noteSession2;
            // console.log("---------"+  e.idNoteElement2[0]+" | "+ e.idNoteElement2[1]);

           },
           (error: any) => {
             console.error('Erreur lors de la récupération de la note élément :', error);
           }
         );
       }
     }


  /*getNoteModule(e: Etudiant) {

    this.noteModuleService.getNoteModuleByModule(this.idnM, e.id).pipe(
      map((noteModule: NoteModule) => {
        e.noteModule = noteModule.noteSession2;
        e.statut = noteModule.statut;
        this.n=noteModule.noteSession2.toString();
        console.log("------------Recuperer la valeur --" + e.noteModule + " | " + e.statut);
      }),
      catchError((error: any) => {
        console.error("Erreur lors de la récupération du module de note :", error);
        return of(""); // Retourner une valeur par défaut en cas d'erreur
      })
    );
  }*/

  getColumnValue(e: Etudiant, index: number): string {

   if (index === 0) {
     // console.log(e.notesElement1[0].noteSession1)
      return e.notesElement1[0] !== -1 ? e.notesElement1[0].toString() : "--";

    } else if (index === 1) {
      return e.notesElement1[1]!== -1 ? e.notesElement1[1].toString() : "--";
    }if (index === 2) {
      return e.notesElement2[0]!== -1 ? e.notesElement2[0].toString() : "--";
    }else if (index === 3) {
      return e.notesElement2[1]!== -1 ? e.notesElement2[1].toString() : "--";
    }
    e.editMode[index] = !e.editMode[index];


  }

  setColumnValue(value: string, e: Etudiant, index: number) {
    if (index === 0) {
       e.notesElement1[0] = parseFloat(value);

       let noteM : NoteElement={
         id:e.idNotesElement1[0],
         noteSession1:  parseFloat(value),
         noteSession2:null,
      };
       console.log("-----------------"+noteM.id+"|"+noteM.noteSession1)
       let ne : Observable<NoteElement>;
       ne = this.noteElementService.updateNoteElement(e.idNotesElement1[0],noteM);
       ne.forEach((n:NoteElement)=>{
        // console.log("-------looooooool"+n.id+"|"+n.noteSession1);
       })


    } else if (index === 1) {
      e.notesElement1[1]= parseFloat(value);
      let noteM : NoteElement={
        id:e.idNotesElement1[1],
        noteSession1:  null,
        noteSession2:  parseFloat(value),
      };
      console.log("-----------------"+noteM.id+"|"+noteM.noteSession2)
      let ne : Observable<NoteElement>;
      ne = this.noteElementService.updateNoteElement(e.idNotesElement1[1],noteM);


      console.log("------------STATUT1 --"+e.noteModule+" | "+e.statut);



    }if (index === 2) {
      e.notesElement2[0]= parseFloat(value);
      let noteM2 : NoteElement={
        id:e.idNoteElement2[0],
        noteSession1:  parseFloat(value),
        noteSession2:null,
      };
      console.log("-----------------"+noteM2.id+"|"+noteM2.noteSession1)
      let ne : Observable<NoteElement>;
       this.noteElementService.updateNoteElement(noteM2.id,noteM2).subscribe((nE: NoteElement) => {
         console.log("--------Verifier Note Zlement session 2" + nE.id + " | " + nE.noteSession1 + " | " + nE.noteSession2);
       });

    }else if (index === 3) {
     e.notesElement2[1]= parseFloat(value);
      let noteM : NoteElement={
        id:e.idNoteElement2[1],
        noteSession1:  null,
        noteSession2:  parseFloat(value),
      };
      console.log("-----------------"+noteM.id+"|"+noteM.noteSession1)
      let ne : Observable<NoteElement>;
      ne = this.noteElementService.updateNoteElement(e.idNoteElement2[1],noteM);


    }


  }

  toggleEditMode(etudiant: any, index: number) {
    // Bascule l'état editMode de la colonne correspondante
    // Utilisez l'index pour accéder à la colonne spécifique dans l'objet etudiant
    etudiant.editMode[index] = !etudiant.editMode[index];
  }



}
