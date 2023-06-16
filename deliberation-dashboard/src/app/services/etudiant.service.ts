import { Injectable } from '@angular/core';
import {EtudiantModel} from "../pages/model/etudiant.model";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {

  constructor(private http:HttpClient) { }

  saveEtudiant(etud: EtudiantModel) {
    return this.http.post(environment.backendHost+"/api/etudiant/add",etud);

  }
}
