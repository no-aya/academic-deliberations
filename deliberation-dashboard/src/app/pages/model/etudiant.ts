export interface Etudiant {
  id : number;
  apogee : string;
  nom : string;
  prenom : string;
  idNotesElement1:number[];
  notesElement1: number[];
  notesElement2: number[];
  idNoteElement2:number[];
  editMode : boolean[];
}
