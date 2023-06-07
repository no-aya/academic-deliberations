import { Component, OnInit } from '@angular/core';
import {Session} from "../model/session.model";
import {catchError, Observable, throwError} from "rxjs";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SessionService} from "../../services/session.service";

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  sessions!: Observable<Array<Session>>;
  errorMessage!: string;


  isClosed: boolean;
  constructor(private sessionService : SessionService) { }

  ngOnInit(): void {
    this.sessions=this.sessionService.getSessions().pipe(
      catchError(err => {
        this.errorMessage=err.message;
        return throwError(err);
      }
      ));
  }


  handleCloseSession(s: Session) {
    console.log(s.id);
    let conf = confirm("Are you sure?");
    if(!conf) return;
    s.isClosed=!s.isClosed;
    this.sessionService.updateSession(s).subscribe({
      next : (resp) => {
        this.sessions=this.sessions.pipe(
          catchError(err => {
            this.errorMessage=err.message;
            return throwError(err);
          })
        );
      },
      error : err => {
        console.log(err);
      }
    })

  }
}
