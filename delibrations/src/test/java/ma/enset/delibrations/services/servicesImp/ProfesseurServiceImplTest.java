package ma.enset.delibrations.services.servicesImp;

import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.exceptions.ProfesseurNotFoundException;
import ma.enset.delibrations.services.ProfesseurService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProfesseurServiceImplTest {

    @Autowired
    private ProfesseurService professeurService;

    @Test
    void shouldCreateProfesseur() throws ProfesseurNotFoundException, CannotProceedException, ElementNotFoundException {
        //test unitaire pour la creation d'un professeur
        ProfesseurRequestDTO expectedProfesseur = ProfesseurRequestDTO.builder()
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .email("email")
                .build();

        ProfesseurResponseDTO savedProfesseur = professeurService.createProfesseur(expectedProfesseur);

        assertNotNull(savedProfesseur);
        assertEquals(expectedProfesseur.getCin(), savedProfesseur.getCin());
        assertEquals(expectedProfesseur.getNom(), savedProfesseur.getNom());
        assertEquals(expectedProfesseur.getPrenom(), savedProfesseur.getPrenom());
        assertEquals(expectedProfesseur.getEmail(), savedProfesseur.getEmail());


    }

    @Test
    void updateProfesseur() throws ProfesseurNotFoundException, ElementNotFoundException, CannotProceedException {
        ProfesseurRequestDTO expectedProfesseur = ProfesseurRequestDTO.builder()
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .email("email")
                .build();

        ProfesseurResponseDTO savedProfesseur = professeurService.createProfesseur( expectedProfesseur);

        expectedProfesseur.setNom("nom updated");

        ProfesseurResponseDTO updatedProfesseur = professeurService.updateProfesseur(expectedProfesseur.getCin(), expectedProfesseur);

        assertNotNull(updatedProfesseur);
        assertEquals(expectedProfesseur.getCin(), updatedProfesseur.getCin());
        assertEquals(expectedProfesseur.getNom(), updatedProfesseur.getNom());
        assertEquals(expectedProfesseur.getPrenom(), updatedProfesseur.getPrenom());
        assertEquals(expectedProfesseur.getEmail(), updatedProfesseur.getEmail());



    }

    @Test
    void shouldDeleteProfesseur() throws ProfesseurNotFoundException, CannotProceedException, ElementNotFoundException {
        ProfesseurRequestDTO expectedProfesseur = ProfesseurRequestDTO.builder()
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .email("email")
                .build();

        ProfesseurResponseDTO savedProfesseur = professeurService.createProfesseur(expectedProfesseur);

        professeurService.deleteProfesseur(savedProfesseur.getCin());

        assertThrows(ProfesseurNotFoundException.class, () -> professeurService.getProfesseur(savedProfesseur.getCin()));


    }

    @Test
    void shouldGetProfesseur() throws ProfesseurNotFoundException, CannotProceedException, ElementNotFoundException {
        ProfesseurRequestDTO expectedProfesseur = ProfesseurRequestDTO.builder()
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .email("email")
                .build();

        ProfesseurResponseDTO savedProfesseur = professeurService.createProfesseur(expectedProfesseur);

        ProfesseurResponseDTO professeur = professeurService.getProfesseur(savedProfesseur.getCin());

        assertNotNull(professeur);
        assertEquals(expectedProfesseur.getCin(), professeur.getCin());
        assertEquals(expectedProfesseur.getNom(), professeur.getNom());
        assertEquals(expectedProfesseur.getPrenom(), professeur.getPrenom());
        assertEquals(expectedProfesseur.getEmail(), professeur.getEmail());

    }


}
