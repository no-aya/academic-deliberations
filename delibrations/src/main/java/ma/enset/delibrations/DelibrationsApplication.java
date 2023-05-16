package ma.enset.delibrations;

import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@SpringBootApplication
public class DelibrationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelibrationsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProfesseurRepository professeurRepository, ModuleRepository moduleRepository,
                            ElementRepository elementRepository, NoteSemestreRepository noteSemestreRepository, SemestreRepository semestreRepository, NoteElementRepository noteElementRepository, DepartementRepository departementRepository, FiliereRepository filiereRepository) {
        return args -> {
            AtomicInteger i = new AtomicInteger();
            //Testing Element
            Stream.of("Module1","Module2","Module3","Module4","Module5","Module6","Module7","Module8","Module9","Module10").forEach(module->{
                Element element = new Element();
                element.setCode("CODE"+(i.getAndIncrement()));
                element.setTitre(module);
                element.setPonderation(0.5F);
                elementRepository.save(element);
            });

            AtomicInteger c = new AtomicInteger();
            //Testing Professeur
            Stream.of("Professeur1","Professeur2","Professeur3","Professeur4","Professeur5","Professeur6","Professeur7","Professeur8","Professeur9","Professeur10").forEach(professeur->{
                Professeur prof = new Professeur();
                prof.setNom(professeur);
                prof.setPrenom(professeur);
                prof.setCin("EE929292"+c.getAndIncrement());
                prof.setEmail(professeur+"@gmail.com");
                prof.setTelephone("0606060606");
                prof.setAdresse("Adresse "+professeur);
                prof.setCreatedAt(new Date());
                prof.setElementModules(elementRepository.findAll());
                professeurRepository.save(prof);

            });

            Element element = elementRepository.findByCode("CODE1");
            Element element2 = elementRepository.findByCode("CODE2");
            Professeur prof = professeurRepository.findByNom("Professeur1");

            prof.getElementModules().add(element);
            prof.getElementModules().add(element2);
            element.setProfesseur(prof);
            element2.setProfesseur(prof);
            professeurRepository.save(prof);
            elementRepository.save(element);
            elementRepository.save(element2);

            System.out.println("Professeur: "+prof.getPrenom() + " " + prof.getNom());
            System.out.println("Elements : ");
            prof.getElementModules().forEach(e->{
                System.out.println(e.getCode() + " " + e.getTitre());
            });
            //Testing Module
            /*Stream.of("Module1","Module2","Module3","Module4","Module5","Module6","Module7","Module8","Module9","Module10").forEach(module->{
                Module module1 = new Module();
                module1.setIdModule(module+"ID");
                module1.setIntitule(module);
                moduleRepository.save(module1);
            });*/
            //Testing Semestre
            Stream.of("Semestre1","Semestre2","Semestre3","Semestre4","Semestre5","Semestre6","Semestre7","Semestre8","Semestre9","Semestre10").forEach(semestre->{
                Semestre sem = new Semestre();
                sem.setCode("CODE"+(i.getAndIncrement()));
                sem.setLibelle(semestre);
                semestreRepository.save(sem);
            });
            //Testing NoteSemestre
            Stream.of("NoteSemestre1","NoteSemestre2","NoteSemestre3","NoteSemestre4","NoteSemestre5","NoteSemestre6","NoteSemestre7","NoteSemestre8","NoteSemestre9","NoteSemestre10").forEach(noteSemestre->{
                NoteSemestre note = new NoteSemestre();
                note.setSemestre(semestreRepository.findByCode("CODE10"));
                note.setNoteSession2(10.0F);
                note.setNoteSession1(10.0F);
                noteSemestreRepository.save(note);
            });
          
            Element element1 = elementRepository.findByCode("CODE1");
            //Testing NoteElement
            Stream.of(15.2,15.3,12.4, 14.5, 16.5, 17.5, 18.5, 19.5, 20.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0).forEach(noteElement->{
                NoteElement note = new NoteElement();
                note.setNoteSession1(noteElement);
                note.setNoteSession2(noteElement);
                note.setElement(element1);
                note.setCreatedAt(new Date());
                noteElementRepository.save(note);
                element1.getNoteElement().add(note);
                elementRepository.save(element1);


            });

            //testing Departement
            Stream.of("Departement1","Departement2","Departement3","Departement4","Departement5","Departement6","Departement7").forEach(departement->{
                Departement depart = new Departement();
                depart.setIntitule(departement);
                depart.setCode(departement);
                depart.setCreatedAt(new Date());
                depart.setFilieres(filiereRepository.findAll());
                departementRepository.save(depart);
            });


            Departement departement1 = departementRepository.findByCode("Departement1");
            //testing Filiere
            Stream.of("Filiere1","Filiere2","Filiere3").forEach(filiere->{
                Filiere fil = new Filiere();
                fil.setIntitule(filiere);
                fil.setCode(filiere);
                fil.setCreatedAt(new Date());
                fil.setDepartement(departement1);
                filiereRepository.save(fil);
            });


        };
    }

}
