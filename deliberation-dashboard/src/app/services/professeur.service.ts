import { Injectable } from '@angular/core';
import {ProfesseurModel} from "../pages/model/professeur.model";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProfesseurService {

  constructor(private http:HttpClient) { }

  saveProfesseur(prof: ProfesseurModel) {
    return this.http.post(environment.backendHost+"/api/professeur/add",prof);

  }
}
