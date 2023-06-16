import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {catchError, Observable, pipe, throwError} from "rxjs";
import {AnneeUniv} from "../../model/anneeUniv.model";
import {DepAr, ElementAr, FiliereAr, ModuleAr} from "../../model/arbreDep.model";
import {ModuleEditModel} from "../../model/moduleEdit.model";
import {ElementModel} from "../../model/element.model";
import {DepartementsAdminService} from "../../../services/departements-admin.service";
import {FilieresAdminService} from "../../../services/filieres-admin.service";
import {ModulesAdminService} from "../../../services/modules-admin.service";
import {DepartementEditModel} from "../../model/departementEdit.model";
import {FiliereEditModel} from "../../model/filiereEdit.model";
import {ElementAdminTsService} from "../../../services/element-admin.ts.service";



@Component({
  selector: 'app-modules-admin',
  templateUrl: './modules-admin.component.html',
  styleUrls: ['./modules-admin.component.css']
})
export class ModulesAdminComponent implements OnInit {
  idProf: number;
  deptFormGroup!: FormGroup;
  libelS!: string;
  depArray: DepAr[] = [];
  semestres: DepAr[] = [];
  depAr: DepAr;
  filiereAr: FiliereAr;

  departments!: Observable<DepartementEditModel[]>;
  filieres!: Observable<FiliereEditModel[]>;
  modules!: Observable<ModuleEditModel[]>;
  elements!: Observable<ElementModel[]>;

  constructor(
    private fb: FormBuilder,
    private departementService: DepartementsAdminService,
    private filiereService: FilieresAdminService,
    private moduleService: ModulesAdminService,
    private elementService: ElementAdminTsService
  ) {}

  ngOnInit() {
    this.idProf = 1;
    this.deptFormGroup = this.fb.group({
      libelS: this.fb.control('Semestre1'),
    });
  }

  searchDept() {
    this.libelS = this.deptFormGroup.value.libelS;
    this.depArray = [];

    this.departments = this.departementService.getDepartements();
    this.departments.subscribe((dep: DepartementEditModel[]) => {
      dep.forEach((d: DepartementEditModel, index: number) => {
        if (!this.depArray) {
          this.depArray = [];
        }
        if (!this.depArray[index]) {
          this.depArray[index] = {
            id: 0,
            intitule: '',
            filiereChildren: [],
          };
        }

        this.searchFiliere(d, this.depArray[index]);
      });
    });
  }

  private searchFiliere(dep: DepartementEditModel, depAr: DepAr): void {
    // Récupérer les filières par département, année et semestre
    this.filieres = this.filiereService.getFiliereByProfAndDep(this.idProf, dep.id, this.libelS).pipe(
      catchError((error) => {
        return throwError('Something went wrong!');
      })
    );

    this.filieres.subscribe((filieres: FiliereEditModel[]) => {
      if (depAr.filiereChildren) {
        filieres.forEach((filiere: FiliereEditModel) => {
          let fAr: FiliereAr = {
            id: 0,
            intitule: '',
            moduleChildren: [],
          };
          fAr.id = filiere.id;
          fAr.intitule = filiere.intitule;
          depAr.filiereChildren.push(fAr);
          this.searchModule(fAr);
        });
      }
    });
  }

  private searchModule(fAr: FiliereAr): void {
    // Récupérer les modules par filière, année et semestre
    this.modules = this.moduleService.getModuleByProfAndFiliere(this.idProf, fAr.id, this.libelS).pipe(
      catchError((error) => {
        return throwError('Something went wrong!');
      })
    );

    this.modules.subscribe((modules: ModuleEditModel[]) => {
      if (fAr.moduleChildren) {
        modules.forEach((module: ModuleEditModel) => {
          let mAr: ModuleAr = {
            id: 0,
            expanded: false,
            intitule: '',
            elementChildren: [],
            etudiants: [],
          };
          mAr.id = module.id;
          mAr.intitule = module.intitule;
          fAr.moduleChildren.push(mAr);
          this.searchElement(mAr);
        });
      }
    });
  }

  private searchElement(mAr: ModuleAr) {
    // Récupérer les éléments par module, année et semestre
    this.elements = this.elementService.getElementByProfAndModule(this.idProf, mAr.id, this.libelS).pipe(
      catchError((error) => {
        return throwError('Something went wrong!');
      })
    );

    this.elements.subscribe((elements: ElementModel[]) => {
      if (mAr.elementChildren) {
        elements.forEach((element: ElementModel) => {
          let eAr: ElementAr = {
            id: 0,
            code: '',
            titre: '',
          };
          eAr.id = element.id;
          eAr.code = element.code;
          eAr.titre = element.titre;
          mAr.elementChildren.push(eAr);
        });
      }
    });
  }
}


  /*searchElement(moduleAr: ModuleAr): void {
    // Récupérer les éléments par module, année et semestre
    this.elements = this.elementService
      .getElementByProfAndModule(this.idProf,this.f, this.libelS)
      .pipe(
        catchError((error) => {
          return throwError('Something went wrong!');
        })
      );

    this.elements.subscribe((elements: Array<ElementModel>) => {
      if (moduleAr.elementChildren) {
        elements.forEach((element: ElementModel) => {
          let eAr: ElementAr = {
            id: 0,
            code: '',
            titre: '',
          };
          eAr.id = element.id;
          eAr.code = element.code;
          eAr.titre = element.titre;
          moduleAr.elementChildren.push(eAr);
        });
      }
    });
  }*/

 /* searchEtudiant(m:ModuleAr){
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
  }*/

 /* serchNoteElement(e:Etudiant,m : ModuleAr, el1 : ElementModel, el2 : ElementModel){
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

      this.noteElement2.subscribe((n: ElementAr) => {
          if (!e.notesElement2) e.notesElement2=[];
        //  e.notesElement2[0]= n.noteSession1==null?-1:n.noteSession1;
         // e.notesElement2[1]= n.noteSession2==null?-1:n.noteSession2;
          console.log("---------noteEL2"+m.intitule+" | "+  el1.titre+" | "+el2.titre)

        },
        (error: any) => {
          console.error('Erreur lors de la récupération des éléments :', error);
        }
      );
    }
  }*/





  /*modules!: Observable<Array<ModuleEditModel>>;
  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;
 // semestre: { [key: number]: Semestre } = {};
  intituleFiliere: string;
  constructor(
    private modulesAdminService: ModulesAdminService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control("")
    });

    this.modules = this.modulesAdminService.getModules().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }



  handleSearchModules() {
    let kw = this.searchFormGroup?.value.keyword;
    this.modules = this.modulesAdminService.searchModules(kw).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleDeleteModule(d: ModuleEditModel) {
    let conf = confirm('Are you sure?');
    if (!conf) return;

    this.modulesAdminService.deleteModule(d.code).subscribe({
      next: (resp) => {
        this.modules = this.modules.pipe(
          map(data => {
            let index = data.indexOf(d);
            data.slice(index, 1);
            return data;
          })
        );
      },
      error: err => {
        console.log(err);
      }
    });
  }

  handleEditModule(module: ModuleEditModel) {
    this.router.navigateByUrl('/edit-module/' + module.id, { state: module });
  }
}
*/
