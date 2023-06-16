import {Etudiant} from "./etudiant";

export interface DepAr {
  id : number;
  intitule : string;
  expanded?: boolean;
  filiereChildren: FiliereAr[];
}

export interface FiliereAr {
  id : number;
  intitule : string;
  expanded?: boolean;
  moduleChildren: ModuleAr[];
}

export interface ModuleAr {
  id : number;
  expanded?: boolean;
  intitule : string;
  elementChildren: ElementAr[];
  etudiants: Etudiant[];
}

export interface ElementAr {
  id : number;
  code : string;
  titre : string;
}



