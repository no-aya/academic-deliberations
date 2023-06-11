import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { DepartementsComponent } from '../../pages/departments/departements.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import {ComptesComponent} from "../../pages/comptes/comptes.component";
import {DepartementsEditComponent} from "../../pages/departements-edit/departements-edit.component";
import {SessionComponent} from "../../pages/session/session.component";
import {ProfEtudComponent} from "../../pages/prof-etud/prof-etud.component";

export const AdminLayoutRoutes: Routes = [
  //  { path: 'dashboard',      component: DashboardComponent },
   // { path: 'user-profile',   component: UserProfileComponent },
    //{ path: 'tables',         component: TablesComponent },
//    { path: 'departements',   component: DepartementsComponent },
    {path: 'comptes',         component:ComptesComponent},
    {path:'departements-edit',component: DepartementsEditComponent},
    {path:'session', component: SessionComponent},
    {path:'prof-etud', component: ProfEtudComponent}
];
