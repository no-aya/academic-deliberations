import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {NoteElement} from "../../pages/model/noteElement.model";
import {environment} from "../../../environments/environment";
import {NoteModule} from "../../pages/model/noteModule.model";

@Injectable({
  providedIn: 'root'
})
export class NoteModuleService {

  constructor(private http:HttpClient) { }

  public getNoteModuleByModule(idModule : number, idEtu : number):Observable<NoteModule>{
    return this.http.get<NoteModule>(environment.backendHost+"/api/note-module/find?idModule="+idModule+"&idEtu="+idEtu);
  }

  public updateNoteModule(id : number ,noteModule :NoteModule):Observable<NoteModule>{
    return this.http.post<NoteModule>(environment.backendHost+"/api/note-module/"+id,noteModule);
  }
}
