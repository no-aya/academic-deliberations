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
                            ElementRepository elementRepository, NoteSemestreRepository noteSemestreRepository, SemestreRepository semestreRepository, NoteElementRepository noteElementRepository, DepartementRepository departementRepository, FiliereRepository filiereRepository,
                            AnneeUnivRepository anneeUnivRepository, NoteModuleRepository noteModuleRepository,
                            InscriptionPedagogiqueRepository inscriptionPedagogiqueRepository,
                            EtudiantRepository etudiantRepository,
                            AppUserRepository appUserRepository,
                            SessionRepository sessionRepository) {
        return args -> {
        };
    }
}
