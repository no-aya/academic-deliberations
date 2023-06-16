package ma.enset.delibrations;

import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.enums.Sexe;
import ma.enset.delibrations.repositories.*;
import ma.enset.delibrations.security.AppUser;
import ma.enset.delibrations.security.repository.AppUserRepository;
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


    //@Autowired
    //private FileStorageService fileStorageService;
    @Bean
    CommandLineRunner start(ProfesseurRepository professeurRepository, ModuleRepository moduleRepository,
                            ElementRepository elementRepository, NoteSemestreRepository noteSemestreRepository, SemestreRepository
                                    semestreRepository, NoteElementRepository noteElementRepository, DepartementRepository
                                    departementRepository, FiliereRepository filiereRepository,
                            AnneeUnivRepository anneeUnivRepository,
                            InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository,
                            EtudiantRepository etudiantRepository,
                            AppUserRepository appUserRepository,
                            SessionRepository sessionRepository) {
        return args -> {




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

                ins.setCreatedAt(new Date());

                inscriptionPedagogiqueRepository.save(ins);
            });

            //Testin AppUser
            Stream.of("Mohammed","Azzeddine","Soumia","Rabia","Aziz").forEach(user->{
                AppUser appUser = new AppUser();
                appUser.setUsername(user);
                appUser.setEmail(user+"@gmail.com");
                appUser.setLastname(user+"Majdi");
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
