import {Component, OnInit, OnDestroy, NgModule} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
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
    private router: Router,
    public fb: FormBuilder
  ) { }

  ngOnInit(): void {

  }

  login(){
    let user = "admin@enset-media.ac.ma"
    let pass = "value"
    console.log(user, pass)
    //test if it's an admin
    if(user == 'admin@enset-media.ac.ma' && pass == 'admin'){
      this.router.navigate(['/dashboard']).then(r => console.log(r));
    }else{
      this.router.navigate(['/login']).then(r => console.log(r));
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
