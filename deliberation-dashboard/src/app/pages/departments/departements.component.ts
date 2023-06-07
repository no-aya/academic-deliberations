import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import {catchError, Observable, throwError} from "rxjs";
import {DepartementService} from "../../services/DepartementService/departement.service";
import {Departement} from "../model/departement.model";

interface Link {
  id: number;
  label: string;
  children: string[];
  expanded: boolean;
}

@Component({
  selector: 'app-departements',
  templateUrl: './departements.component.html',
  styleUrls: ['./departements.component.scss'],
})
export class DepartementsComponent implements OnInit {
 /* departments = [
    ['Génie Informatique', 3],
    ['Génie Electrique', 2],
    ['Génie Mécanique', 2],
    ['Génie Civil', 2],
  ];
*/
  links: Link[] = [
    {
      id: 1,
      label: 'Lien 1',
      children: ['Sous-lien 1', 'Sous-lien 2'],
      expanded: false
    },
    {
      id: 2,
      label: 'Lien 2',
      children: [],
      expanded: false
    },
    {
      id: 3,
      label: 'Lien 3',
      children: ['Sous-lien 3', 'Sous-lien 4'],
      expanded: false
    }
  ];

  toggleLink(link: Link): void {
    link.expanded = !link.expanded;
  }


  departments! : Observable<Array<Departement>>;
  constructor(private departementService: DepartementService) {
  }

  ngOnInit() {
    let idProf=1;
    this.departments=this.departementService.getDepartementsByProf(idProf).pipe(
      catchError(err=>{
        return throwError(err);
      }))
  }


}
