import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Session} from "../pages/model/session.model";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http:HttpClient) { }

  getSessions(): Observable<Array<Session>> {
    return this.http.get<Array<Session>>(environment.backendHost+"/sessions");
  }

  saveSession(s: Session) {
    return this.http.post(environment.backendHost+"/sessions",s);
  }

  updateSession(s: Session) {
    return this.http.post(environment.backendHost+"/updateSessions/"+s.id,s);

  }

  closeSession(s: Session) {
    return this.http.post(environment.backendHost+"/close/"+s.id,s);

  }
}
