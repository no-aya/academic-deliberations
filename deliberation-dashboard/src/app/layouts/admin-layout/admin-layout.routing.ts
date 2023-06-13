import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { IconsComponent } from '../../pages/icons/icons.component';
import { DepartementsComponent } from '../../pages/departments/departements.component';
import { UserProfileComponent } from '../../pages/user-profile/user-profile.component';
import { TablesComponent } from '../../pages/tables/tables.component';
import {ComptesComponent} from "../../pages/comptes/comptes.component";
import {DepartementsEditComponent} from "../../pages/departements-edit/departements-edit.component";
import {SessionComponent} from "../../pages/session/session.component";
import {
  DepartementsAdminComponent
} from "../../pages/departements-edit/departements-admin/departements-admin.component";
import {FilieresAdminComponent} from "../../pages/departements-edit/filieres-admin/filieres-admin.component";
import {ModulesAdminComponent} from "../../pages/departements-edit/modules-admin/modules-admin.component";
import {AddEditDepartComponent} from "../../pages/departements-edit/add-edit-depart/add-edit-depart.component";
import {EditDepartementComponent} from "../../pages/departements-edit/edit-departement/edit-departement.component";
import {AddModuleComponent} from "../../pages/departements-edit/add-module/add-module.component";
import {EditModuleComponent} from "../../pages/departements-edit/edit-module/edit-module.component";
import {AddFiliereComponent} from "../../pages/departements-edit/add-filiere/add-filiere.component";
import {EditFiliereComponent} from "../../pages/departements-edit/edit-filiere/edit-filiere.component";

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'tables',         component: TablesComponent },
    { path: 'icons',          component: IconsComponent },
    { path: 'departements',   component: DepartementsComponent },
    {path: 'comptes', component:ComptesComponent},
    {path:'departements-edit', component: DepartementsEditComponent},
    {path:'departements-admin', component: DepartementsAdminComponent},
    {path:'add-edit-departement', component: AddEditDepartComponent},
    {path:'edit-departement/:id', component: EditDepartementComponent},
    {path:'filieres-admin', component: FilieresAdminComponent},
    {path:'modules-admin', component: ModulesAdminComponent},
    {path:'add-module', component: AddModuleComponent},
    {path:'edit-module', component: EditModuleComponent},
  {path:'add-filiere', component: AddFiliereComponent},
  {path:'edit-filiere/:code', component: EditFiliereComponent},
    {path:'session', component: SessionComponent}
];
