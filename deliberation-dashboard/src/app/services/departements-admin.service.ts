import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DepartementEditModel} from "../pages/model/departementEdit.model";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class DepartementsAdminService {

  constructor(private http:HttpClient) { }
  public getDepartment(id: number): Observable<DepartementEditModel> {
    return this.http.get<DepartementEditModel>(environment.backendHost + "/api/department/" + id);
  }

  public getDepartements():Observable<Array<DepartementEditModel>>{
    return this.http.get<Array<DepartementEditModel>>(environment.backendHost+"/api/departement/all")
  }
  public searchDepartements(keyword : string):Observable<Array<DepartementEditModel>>{
    return this.http.get<Array<DepartementEditModel>>(environment.backendHost+"/api/departement/search?keyword="+keyword)

  }

  public saveDepartement(departement: DepartementEditModel):Observable<DepartementEditModel>{
    return this.http.post<DepartementEditModel>(environment.backendHost+"/api/departement/add",departement);
  }

  public deleteDepartement(code: string){
    return this.http.delete(environment.backendHost+"/api/department/"+code);
  }

  public editDepartment(id : number, departement :DepartementEditModel): Observable<DepartementEditModel> {
    return this.http.put<DepartementEditModel>(environment.backendHost+"/api/department/"+id,departement);
  }
  //ajouté
  public getDepartementsByProf(idProf : number, libelS: string):Observable<Array<DepartementEditModel>>{
    return this.http.get<Array<DepartementEditModel>>(environment.backendHost+"/api/departement?idProf="+idProf+"&libelS="+libelS);
  }

}
