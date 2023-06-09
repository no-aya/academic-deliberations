import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../pages/model/user.model";
import {environment} from "../../../environments/environment";
import {AnneeUniv} from "../../pages/model/anneeUniv";

@Injectable({
  providedIn: 'root'
})
export class AnneeUnivService {

  constructor(private http:HttpClient) { }

  public getAllAnneuniv():Observable<Array<AnneeUniv>>{
    return this.http.get<Array<AnneeUniv>>(environment.backendHost+"/api/annee-univ/all");
  }
}
