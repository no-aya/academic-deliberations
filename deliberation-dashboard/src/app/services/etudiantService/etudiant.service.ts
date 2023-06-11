import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Element} from "../../pages/model/element.model";
import {environment} from "../../../environments/environment";
import {Etudiant} from "../../pages/model/etudiant";

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {


  constructor(private http:HttpClient) { }
  public getEtudiantByInscriptionAndModule(idModule : number):Observable<Array<Etudiant>>{
    return this.http.get<Array<Etudiant>>(environment.backendHost+"/api/etudiant/inscriptionPedagogique/"+idModule);
  }
}
