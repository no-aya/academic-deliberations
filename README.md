# Gestion des déliberations : Projet d'innovation 2022-2023

```
Auteurs : 
- AITOULAHYANE Aya
- BOUSSAIRI Ikram
- ILLOUSSAMEN Asmae
- LOUKILI Imane
- SALSABIL Massabih

Date : 26/02/2023

Version : 1.0
```

## Introduction
La digitalisation est devenue un enjeu majeur dans de nombreux domaines, y compris dans le secteur de l'éducation. Ainsi, dans le cadre de la modernisation de l'ENSET, nous avons été mandatés pour développer un système de gestion des délibérations qui s'intègrera à l'ERP de l'école. Cette solution innovante permettra de faciliter la gestion des délibérations en prenant en compte les absences des étudiants, tout en offrant une fonction d'archivage et de consultation. Grâce à ce nouvel outil, l'ENSET pourra rationaliser ses processus administratifs et améliorer l'efficacité de son fonctionnement. Dans cette optique, notre équipe est fière de mettre son expertise à la disposition de l'établissement pour contribuer à sa transformation digitale et à son développement future.

Le système de gestion des délibérations que nous développons pour l'ENSET offrira de multiples fonctionnalités pour répondre aux besoins spécifiques de l'établissement. Tout d'abord, il permettra de gérer efficacement les délibérations en prenant en compte les absences des étudiants lors des examens, ce qui facilitera le traitement des résultats et permettra de générer des statistiques fiables. Ensuite, notre solution offrira une fonctionnalité d'archivage qui permettra de stocker de manière sécurisée l'ensemble des délibérations, afin de faciliter leur consultation ultérieure. Les enseignants et les responsables administratifs de l'ENSET pourront ainsi accéder facilement aux délibérations passées pour effectuer des comparaisons et des analyses. Enfin, notre système offrira également une fonctionnalité de consultation en temps réel des délibérations en cours, ce qui permettra aux enseignants et aux responsables administratifs de suivre l'évolution des résultats des étudiants au fil des examens.

Notre solution de gestion des délibérations sera un véritable outil de gestion pour l'ENSET, permettant d'améliorer l'efficacité de son fonctionnement et de garantir la qualité de son enseignement.


## Sommaire
- [Généralités](#generalites)
- [Fonctionnalités](#fonctionnalites)
- [User Stories](#user-stories)
- [Diagramme de classes](#diagramme-de-classes)
- [Diagramme de cas d'utilisation](#diagramme-de-cas-dutilisation)
- [Conception](#conception)
- [Développement](#developpement)
- [Tests](#tests)
- [Déploiement](#deploiement)
- [Conclusion](#conclusion)

## Généralités
### Objectifs
L'objectif de ce projet est de développer un système de gestion des délibérations qui fera partie de l'ERP de l'école. Ce système servira d'intermédiaire entre la platforme APOGEE et les enseignants. 

Nous cherchons à faciliter la prise des décisions concernant les délibérations en fournissant des statistiques et des informations utiles aux enseignants. 

### Contexte
L'ENSET est une école d'ingénieurs située à Mohammedia. Elle est composée de 4 départements :
- Département Mathématiques et Informatique
- Département Génie Electrique et Informatique Industrielle
- Département Génie Mécanique et Productique
- Dpartement 

### Contraintes
- Le système doit être développé en utilisant le langage Java.
- Le système doit être développé en utilisant le framework Spring Boot.
- Le système doit être développé en utilisant le framework Angular.
- La version mobile du système doit être développée en utilisant Flutter.
- Le système doit être développé en utilisant la base de données MySQL.

## Fonctionnalités
### Fonctionnalités principales
- Affichage des résultats dépendament de l'enseignant connecté (c-à-d chaque enseigant ne verra que les classes qui lui sont affectées).
- Affichage des statistiques concernant les délibérations.
- Accès au noombres des heures d'abscence de chaque étudiant dépendament de la classe.
- Gestion des délibérations (création, modification, suppression).
- Gestion des classes (création, modification, suppression).
- Gestion des enseignants (création, modification, suppression).
- Gestion des étudiants (création, modification, suppression).

### Fonctionnalités secondaires
- Gestion des utilisateurs (création, modification, suppression).
- Gestion des rôles (création, modification, suppression).
- Gestion des permissions (création, modification, suppression).


## User Stories
Il existe 3 types d'utilisateurs dans le système :
- Administrateur
- Enseignant
- Etudiant

### Administrateur
- En tant qu'administrateur, je veux pouvoir créer un compte pour un enseignant.
- ...

### Enseignant
- En tant qu'enseignant, je veux pouvoir créer un compte pour un étudiant.
- ...

### Etudiant
- En tant qu'étudiant, je veux pouvoir consulter les délibérations.
- ...

## Diagramme de classes
### Description des relations
- Elément de Module (Affecté à) Enseignant matière
- Module (Affecté à) Coordonnateur de module
- Filière (Affecté à) Chef de filière
- Département (Affecté à) Chef de département 
- Etablissement (Affecté à) Administrateur
- Cours (Affecté à) Salle & Enseignant étudiant.

## Diagramme de cas d'utilisation


## Conclusion
La digitalisation de l'ENSET constitue une étape importante pour moderniser l'établissement et améliorer son efficacité. Nous sommes convaincus que notre système de gestion des délibérations répondra parfaitement aux besoins spécifiques de l'école en matière de traitement des résultats des examens, d'archivage et de consultation des délibérations. Nous sommes également engagés à travailler en étroite collaboration avec l'équipe de l'ENSET pour assurer le développement optimal de notre solution, en utilisant les meilleures pratiques et les technologies les plus avancées. 

En utilisant des frameworks pour le développement, nous sommes convaincus que nous pourrons offrir une solution efficace, performante et adaptée aux besoins de l'établissement. Nous sommes impatients de poursuivre cette collaboration fructueuse avec l'ENSET et de contribuer à son développement futur.




