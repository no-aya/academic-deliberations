import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Filiere} from "../../pages/model/filiere.model";
import {environment} from "../../../environments/environment";
import {Module} from "../../pages/model/module.model";

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  constructor(private http:HttpClient) { }

  public getModuleByProfAndFiliere(idProf : number,idFiliere : number, anneeUniv : string, semestre: string):Observable<Array<Module>>{
    return this.http.get<Array<Module>>(environment.backendHost+"/api/modules?idProf="+idProf+"&idFiliere="+idFiliere+"&codeAnnee="+anneeUniv+"&libelS="+semestre);
  }
}
