import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {DepartementEditModel} from "../pages/model/departementEdit.model";


@Injectable({
  providedIn: 'root'
})

export class DepartementsEditService {

  constructor(private http:HttpClient) { }

  public getDepartements():Observable<Array<DepartementEditModel>>{
    return this.http.get<Array<DepartementEditModel>>(environment.backendHost+"/api/departement/all")
  }
  public searchDepartements(keyword : string):Observable<Array<DepartementEditModel>>{
    return this.http.get<Array<DepartementEditModel>>(environment.backendHost+"/api/departement/search?keyword="+keyword)

  }

  public saveDepartement(departement: DepartementEditModel):Observable<DepartementEditModel>{
    return this.http.post<DepartementEditModel>(environment.backendHost+"/api/departement/add",departement);

  }
  public deleteDepartement(code: String){
    return this.http.delete(environment.backendHost+"/api/departement/"+code);
  }

}
