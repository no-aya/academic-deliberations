export interface Filiere {
  id : number;
  code : string;
  intitule : string;
  expanded?: boolean;
  moduleChildren?: string[];
}
