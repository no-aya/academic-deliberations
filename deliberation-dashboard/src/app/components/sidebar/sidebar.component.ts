import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
  }
}
