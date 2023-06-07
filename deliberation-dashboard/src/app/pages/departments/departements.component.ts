import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';



@Component({
  selector: 'app-departements',
  templateUrl: './departements.component.html',
  styleUrls: ['./departements.component.css'],
})
export class DepartementsComponent implements OnInit {
  departments = [
    ['Génie Informatique', 3],
    ['Génie Electrique', 2],
    ['Génie Mécanique', 2],
    ['Génie Civil', 2]
  ];

  constructor() {
  }

  ngOnInit() {






      //exporting the data to the html page
  }


}
