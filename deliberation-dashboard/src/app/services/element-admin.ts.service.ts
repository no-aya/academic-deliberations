import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ElementModel} from "../pages/model/element.model";

@Injectable({
  providedIn: 'root'
})
export class ElementAdminTsService {


//ajouté
  constructor(private http:HttpClient) { }
  public getElementByProfAndModule(idProf : number,idModule : number, semestre: string):Observable<Array<ElementModel>>{
    return this.http.get<Array<ElementModel>>(environment.backendHost+"/api/element?idProf="+idProf+"&idModule="+idModule+"&libelS="+semestre);
  }
}
