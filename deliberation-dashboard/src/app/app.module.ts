import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';
import { DepartementsAdminComponent } from './pages/departements-edit/departements-admin/departements-admin.component';
import { FilieresAdminComponent } from './pages/departements-edit/filieres-admin/filieres-admin.component';
import { ModulesAdminComponent } from './pages/departements-edit/modules-admin/modules-admin.component';
import { AddEditDepartComponent } from './pages/departements-edit/add-edit-depart/add-edit-depart.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatDialogModule} from "@angular/material/dialog";
import { EditDepartementComponent } from './pages/departements-edit/edit-departement/edit-departement.component';
import {MatButtonModule} from "@angular/material/button";


@NgModule({
    imports: [
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        ComponentsModule,
        NgbModule,
        RouterModule,
        AppRoutingModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatDatepickerModule,
        MatDialogModule,
        MatButtonModule
    ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    AuthLayoutComponent,
    DepartementsAdminComponent,
    FilieresAdminComponent,
    ModulesAdminComponent,
    AddEditDepartComponent,
    EditDepartementComponent

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
