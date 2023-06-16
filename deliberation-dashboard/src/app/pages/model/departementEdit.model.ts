export interface DepartementEditModel {
  id : number;
  code : string;
  intitule : string;
  createdAt : Date;
  dateMAJ : Date;
  nbrElement : number;
  expanded?: boolean;
  filiereChildren?: string[];

}

