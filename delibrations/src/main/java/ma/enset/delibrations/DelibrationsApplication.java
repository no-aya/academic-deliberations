package ma.enset.delibrations;

import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.security.AppUser;
import ma.enset.delibrations.security.repository.AppUserRepository;
import ma.enset.delibrations.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FileStorageService fileStorageService;
    @Bean
    CommandLineRunner start(ProfesseurRepository professeurRepository, ModuleRepository moduleRepository,
                            ElementRepository elementRepository, NoteSemestreRepository noteSemestreRepository, SemestreRepository semestreRepository, NoteElementRepository noteElementRepository, DepartementRepository departementRepository, FiliereRepository filiereRepository,
                            AnneeUnivRepository anneeUnivRepository,
                            InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository,
                            EtudiantRepository etudiantRepository,
                            AppUserRepository appUserRepository,
                            SessionRepository sessionRepository) {
        return args -> {
            fileStorageService.deleteAll();
            fileStorageService.init();
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
            Stream.of("Module1","Module2","Module3","Module4","Module5","Module6","Module7","Module8","Module9","Module10").forEach(module->{
                Module module1 = new Module();
                module1.setCode(module+"ID");
                module1.setIntitule(module);
                moduleRepository.save(module1);
            });
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

            //Testing Etudiant
            Stream.of("Etudiant1","Etudiant2","Etudiant3","Etudiant4","Etudiant5","Etudiant6","Etudiant7","Etudiant8","Etudiant9","Etudiant10").forEach(etudiant->{
                Etudiant etud = new Etudiant();
                etud.setNom(etudiant);
                etud.setPrenom(etudiant);
                etud.setCne("EE929292"+c.getAndIncrement());
                etud.setEmail(etudiant+"@gmail.com");
                etud.setTelephone("0606060606");
                etud.setAdresse("Adresse "+etudiant);
                etud.setCreatedAt(new Date());
                etudiantRepository.save(etud);
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


            //Testing anneUniv
            Stream.of("20-21","21-22","22-23","23-24","24-25","25-26","26-27","27-28","28-29","29-30").forEach(anneUniv->{
                AnneeUniv anne = new AnneeUniv();
                //Code, date-debut, date-fin
                anne.setCodeAnnee(anneUniv);
                anne.setDateDebut(new Date());
                anne.setDateFin(new Date());
                anneeUnivRepository.save(anne);
            });


            //Testing InscriptionPedagogique
            Stream.of("InscriptionPedagogique1","InscriptionPedagogique2","InscriptionPedagogique3","InscriptionPedagogique4","InscriptionPedagogique5","InscriptionPedagogique6","InscriptionPedagogique7","InscriptionPedagogique8","InscriptionPedagogique9","InscriptionPedagogique10").forEach(inscriptionPedagogique->{
                InscriptionPedagogique ins = new InscriptionPedagogique();
                ins.setModule(moduleRepository.findByCode("Module1ID"));
                ins.setCreatedAt(new Date());
                ins.setEtudiant(etudiantRepository.findByApogeeAndSoftDeleteIsFalse("EE9292920"));
                inscriptionPedagogiqueRepository.save(ins);
            });

            //Testin AppUser
            Stream.of("user1","user2","user3","user4","user5","user6","user7","user8","user9","user10").forEach(user->{
                AppUser appUser = new AppUser();
                appUser.setUsername(user);
                appUser.setEmail(user+"@gmail.com");
                appUser.setLastname("lastname"+user);
                appUser.setPassword("1234");
                appUser.setSuspend(false);
                appUserRepository.save(appUser);
            });

            //Testing sessions
            Stream.of("Session1","Session2").forEach(session->{
                Session session1 = new Session();
                session1.setLibelle(session);
                session1.setDateDebut(new Date());
                session1.setDateFin(new Date());
                sessionRepository.save(session1);
            });


        };
    }


}
