package ma.enset.delibrations;

import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.entities.Professeur;
import ma.enset.delibrations.repositories.ElementRepository;
import ma.enset.delibrations.repositories.ModuleRepository;
import ma.enset.delibrations.repositories.ProfesseurRepository;
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
    CommandLineRunner start(ProfesseurRepository professeurRepository,
                            ElementRepository elementRepository,
                            ModuleRepository moduleRepository){
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

            //Testing Professeur
            Stream.of("Professeur1","Professeur2","Professeur3","Professeur4","Professeur5","Professeur6","Professeur7","Professeur8","Professeur9","Professeur10").forEach(professeur->{
                Professeur prof = new Professeur();
                prof.setNom(professeur);
                prof.setPrenom(professeur);
                prof.setCin("EE929292");
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
                module1.setIdModule(module+"ID");
                module1.setIntitule(module);
                moduleRepository.save(module1);
            });
        };
    }

}
