package ma.enset.delibrations;

import ma.enset.delibrations.entities.*;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.enums.Sexe;
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
                            AppUserRepository appUserRepository) {
        return args -> {
            fileStorageService.deleteAll();
            fileStorageService.init();







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
            Stream.of("user1","user2","user3","user4","user5","user6","user7","user8","user9","user10").forEach(user->{
                AppUser appUser = new AppUser();
                appUser.setUsername(user);
                appUser.setEmail(user+"@gmail.com");
                appUser.setLastname("lastname"+user);
                appUser.setPassword("1234");
                appUser.setSuspend(false);
                appUserRepository.save(appUser);
            });


        };
    }


}
