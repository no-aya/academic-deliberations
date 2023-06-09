export interface Departement {
  id : number;
  code : string;
  intitule : string;
  nbrElement : number;
  expanded?: boolean;
  filiereChildren?: string[];
}
