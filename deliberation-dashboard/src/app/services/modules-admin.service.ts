import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DepartementEditModel} from "../pages/model/departementEdit.model";
import {environment} from "../../environments/environment";
import {ModuleEditModel} from "../pages/model/moduleEdit.model";

@Injectable({
  providedIn: 'root'
})
export class ModulesAdminService {

  constructor(private http:HttpClient) { }
  public getModule(id: number): Observable<ModuleEditModel> {
    return this.http.get<ModuleEditModel>(environment.backendHost + "/api/module/" + id);
  }

  public getModules():Observable<Array<ModuleEditModel>>{
    return this.http.get<Array<ModuleEditModel>>(environment.backendHost+"/api/module/all")
  }
  public searchModules(keyword : string):Observable<Array<ModuleEditModel>>{
    return this.http.get<Array<ModuleEditModel>>(environment.backendHost+"/api/module/search?keyword="+keyword)

  }

  public saveModule(module: ModuleEditModel):Observable<ModuleEditModel>{
    return this.http.post<ModuleEditModel>(environment.backendHost+"/api/module/add",module);

  }
  public deleteModule(code: String){
    return this.http.delete(environment.backendHost+"/api/module/"+code);
  }

  public editModule(code : String, module :ModuleEditModel): Observable<DepartementEditModel> {
    return this.http.put<DepartementEditModel>(environment.backendHost+"/api/module/"+code,module);
  }
}
