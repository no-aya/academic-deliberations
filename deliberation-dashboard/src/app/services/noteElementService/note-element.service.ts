import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Module} from "../../pages/model/module.model";
import {environment} from "../../../environments/environment";
import {NoteElement} from "../../pages/model/noteElement.model";

@Injectable({
  providedIn: 'root'
})
export class NoteElementService {

  constructor(private http:HttpClient) { }

  public getNoteElementByInscriptionPedagogique(idEtu : number,idModule : number, idElement):Observable<NoteElement>{
    return this.http.get<NoteElement>(environment.backendHost+"/api/note-element/inscriptionPedagogique?idEtu="+idEtu+"&idModule="+idModule+"&idElement="+idElement);
  }


  public updateNoteElement(id : number,noteElement : NoteElement):Observable<NoteElement>{
    return this.http.put<NoteElement>(environment.backendHost+"/api/note-element/"+id, noteElement);
  }

}
