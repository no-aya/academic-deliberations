export interface ModuleEditModel{
  id: number;
  code: string;
  intitule: string;
  elements: Element[];
  semestre: Semestre[];
}
export interface Semestre {
  libelle: string;
}
export interface Element {
  code: string;
  titre: string;
  ponderation: number;
  professeur: Professeur;
}
export interface Professeur {
 nom: string;
 prenom: string;
}
