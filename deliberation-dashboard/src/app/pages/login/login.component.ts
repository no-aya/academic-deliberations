import {Component, OnInit, OnDestroy, NgModule} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ApiService} from "../../services/api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup
  constructor(
    private _api : ApiService,
    private _auth: AuthService,
    private router: Router,
    public fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password:['', Validators.required]
    });

  }

  login(){
    let b = this.form.value
    console.log(b)
    if (this.form.invalid) {
      return;
    }
    //test if it's an admin
    if(b.username == 'admin' && b.password == 'admin'){
      this.router.navigate(['dashboard'])
    }
    /*this._api.postTypeRequest('login', b).subscribe((res: any) => {
      console.log(res)
      if(res.access_token){
        this._auth.setDataInLocalStorage('token', res.access_token)
        this.router.navigate(['dashboard'])
      }
    }, err => {
      console.log(err)
    });*/
  }

}
