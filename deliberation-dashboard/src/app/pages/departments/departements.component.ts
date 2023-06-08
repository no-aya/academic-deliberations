import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';



@Component({
  selector: 'app-departements',
  templateUrl: './departements.component.html',
  styleUrls: ['./departements.component.css'],
})
export class DepartementsComponent implements OnInit {
  departments = [
    ['Département MI', 6],
    ['Génie Electrique', 0],
    ['Génie Mécanique', 0],
    ['Génie Civil', 0]
  ];
  elements: (string | (string | number)[][])[][] = [
    [
      "II-BDCC - Ingénierie Informatique : Big Data et Cloud Computing",
      [["Développement web", 40,0,0,0,0,0],
      ["Projet Développement web", 40,0,0,0,0,0],
      ["Programmation fonctionnelle : concepts et outils", 42,0,0,0,0,0],
      ]
    ],
    [
      "GLSID - Génie du Logiciel et des Systèmes Informatiques Distribués",
      [["Développement web", 40,0,0,0,0,0],
      ["Projet Développement web", 40,0,0,0,0,0],
      ["Programmation fonctionnelle : concepts et outils", 40,0,0,0,0,0],
      ]
    ]

  ];

  constructor() {
  }

  ngOnInit() {






      //exporting the data to the html page
  }


}
