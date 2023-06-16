import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../../pages/model/user.model";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Departement} from "../../pages/model/departement.model";

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  constructor(private http:HttpClient) { }

  public getDepartementsByProf(idProf : number, anneeUniv : string, semestre: string):Observable<Array<Departement>>{
    return this.http.get<Array<Departement>>(environment.backendHost+"/api/departements?id="+idProf+"&codeAnnee="+anneeUniv+"&libelS="+semestre);
  }
}
