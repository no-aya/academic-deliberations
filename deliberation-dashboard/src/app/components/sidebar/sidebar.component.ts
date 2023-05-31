import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Tableau de bord',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/icons', title: 'Icons',  icon:'ni-planet text-blue', class: '' },
    { path: '/departements', title: 'Mes Départements',  icon:'ni-pin-3 text-orange', class: '' },
    { path: '/user-profile', title: 'Profile',  icon:'ni-single-02 text-yellow', class: '' },
    { path: '/tables', title: 'Mes Notes',  icon:'ni-bullet-list-67 text-red', class: '' }

];
export const ROUTESAUTH: RouteInfo[] = [
  { path: '/login', title: 'Se connecter',  icon:'ni-key-25 text-info', class: '' },
  { path: '/register', title: 'S\'inscrire',  icon:'ni-circle-08 text-pink', class: '' }
];
export const ROUTESADMIN: RouteInfo[] = [
  { path: '/departements-edit', title:'Gérer les départements', icon:'ni ni-settings', class:''},
  { path:'/comptes', title:'Gérer les comptes', icon:'ni ni-badge', class:'' },
  { path:'/sessions', title:'Gérer les sessions', icon:'ni ni-ui-04', class:''}
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;
  public adminItems: any[];
  public authItems:any[];

  constructor(private router: Router,private http: HttpClient) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });

    this.adminItems = ROUTESADMIN.filter(adminItem => adminItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
    });
    this.authItems=ROUTESAUTH.filter(authItem=>authItem);
  this.router.events.subscribe(()=>{
    this.isCollapsed=true;
  })}
  
  //Uploading files section/method
  fileName = '';
  onFileSelected(event) {
    const file:File = event.target.files[0];
    if (file) {
      this.fileName = file.name;
      const formData = new FormData();
      formData.append("file", file);
      const upload$ = this.http.post(environment.backendHost+"/upload-file", formData);
      upload$.subscribe();
    }
  }
}
