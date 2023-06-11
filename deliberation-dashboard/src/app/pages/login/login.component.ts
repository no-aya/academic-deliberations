import {Component, OnInit, OnDestroy, NgModule} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
import {ApiService} from "../../services/api.service";

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
    let user = this.form.controls.username.value
    let pass = this.form.controls.password.value
    console.log(user, pass)
    if (this.form.invalid) {
      return;
    }
    //test if it's an admin
    if(user == 'admin' && pass == 'admin'){
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

  onsubmit() {
    this.router.navigate(['/dashboard']).then(r => console.log(r));

  }
}
