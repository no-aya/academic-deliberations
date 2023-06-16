import { Component, OnInit } from '@angular/core';
import {Session} from "../model/session.model";
import {catchError, Observable, throwError} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SessionService} from "../../services/session.service";


@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  sessions!: Observable<Array<Session>>;
  errorMessage!: string;
  newSessionFormGroup! : FormGroup;


  closed: boolean;
  constructor(private sessionService : SessionService, private fb:FormBuilder) { }

  ngOnInit(): void {
    this.newSessionFormGroup=this.fb.group({
      libelle : this.fb.control(null, [Validators.required, Validators.minLength(4)]),
      dateDebut : this.fb.control(null,[Validators.required]),
      dateFin : this.fb.control(null,[Validators.required]),
    });

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
    s.closed=!s.closed;
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

  handleSaveSession() {
    let session:Session=this.newSessionFormGroup.value;
    console.log(session);
    this.sessionService.saveSession(session).subscribe({
      next : data=>{
        alert("Session has been successfully saved!");
        //this.router.navigateByUrl("/sessions");

      },
      error : err => {
        console.log(err);
      }
    });
  }
}
