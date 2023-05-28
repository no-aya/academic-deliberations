import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../pages/model/user.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ComptesService {

  constructor(private http:HttpClient) { }

  public getComptes():Observable<Array<User>>{
    return this.http.get<Array<User>>(environment.backendHost+"/users");
  }

  public searchComptes(keyword: string):Observable<Array<User>>{
    return this.http.get<Array<User>>(environment.backendHost+"/users/search?keyword="+keyword);
  }


  deleteCompte(id: number) {
    return this.http.delete(environment.backendHost+"/users/"+id);

  }
}
