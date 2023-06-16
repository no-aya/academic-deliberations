export interface FiliereEditModel {
  id: number;
  code: string;
  createdAt : Date;
  intitule: string;
  departementId : number;
  departementIntitule : string;
  //ajouté
  expanded?: boolean;
  moduleChildren?: string[];
}










