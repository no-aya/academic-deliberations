import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Element} from "../../pages/model/element.model";

@Injectable({
  providedIn: 'root'
})
export class ElementService {

  constructor(private http:HttpClient) { }
  public getElementByProfAndModule(idProf : number,idModule : number):Observable<Array<Element>>{
    return this.http.get<Array<Element>>(environment.backendHost+"/api/elements?idProf="+idProf+"&idDept="+idModule);
  }
}
