import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import { FiliereEditModel } from '../pages/model/filiereEdit.model';

@Injectable({
  providedIn: 'root'
})
export class FilieresAdminService {
  constructor(private http:HttpClient) { }
  public getFiliere(code: String): Observable<FiliereEditModel> {
    return this.http.get<FiliereEditModel>(environment.backendHost + "/api/filiere/" + code);
  }

  public getFilieres():Observable<Array<FiliereEditModel>>{
    return this.http.get<Array<FiliereEditModel>>(environment.backendHost+"/api/filiere/all")
  }
  public searchFilieres(keyword : string):Observable<Array<FiliereEditModel>>{
    return this.http.get<Array<FiliereEditModel>>(environment.backendHost+"/api/filiere/search?keyword="+keyword)

  }

  public saveFiliere(filiere: FiliereEditModel):Observable<FiliereEditModel>{
    return this.http.post<FiliereEditModel>(environment.backendHost+"/api/filiere/add",filiere);

  }
  public deleteFiliere(code: String){
    return this.http.delete(environment.backendHost+"/api/filiere/"+code);
  }

  public editFiliere(code : String, filiere :FiliereEditModel): Observable<FiliereEditModel> {
    return this.http.put<FiliereEditModel>(environment.backendHost+"/api/filiere/"+code,filiere);
  }


//ajouté
  public getFiliereByProfAndDep(idProf : number,idDept : number, semestre: string):Observable<Array<FiliereEditModel>>{
    return this.http.get<Array<FiliereEditModel>>(environment.backendHost+"/api/filiere?idProf="+idProf+"&idDept="+idDept+"&libelS="+semestre);
  }

}
