package ma.enset.delibrations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DelibrationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelibrationsApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            //Testing Element
            Stream.of("Module1","Module2","Module3","Module4","Module5","Module6","Module7","Module8","Module9","Module10").forEach(module->{
                
            });
        };
    }

}
