export interface ModuleEditModel {
  id: number;
  code: string;
  intitule: string;
  filiereId : number;
  filiereIntitule : string ;

  expanded?: boolean;
  elementChildren?: string[];
}




