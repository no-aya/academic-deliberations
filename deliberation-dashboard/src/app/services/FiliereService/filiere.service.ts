import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Departement} from "../../pages/model/departement.model";
import {environment} from "../../../environments/environment";
import {Filiere} from "../../pages/model/filiere.model";

@Injectable({
  providedIn: 'root'
})
export class FiliereService {

  constructor(private http:HttpClient) { }

  public getFiliereByProfAndDep(idProf : number,idDept : number,anneeUniv : string, semestre: string):Observable<Array<Filiere>>{
    return this.http.get<Array<Filiere>>(environment.backendHost+"/api/filieres?idProf="+idProf+"&idDept="+idDept+"&codeAnnee="+anneeUniv+"&libelS="+semestre);
  }
}
