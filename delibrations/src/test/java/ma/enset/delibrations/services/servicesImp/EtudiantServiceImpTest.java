package ma.enset.delibrations.services.servicesImp;

import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.services.EtudiantService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static ma.enset.delibrations.entities.enums.Sexe.Femme;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class EtudiantServiceImpTest {


    @Autowired
    private EtudiantService etudiantService;
    @Test
    void shouldCreateEtudiant() throws CannotProceedException {
        //test unitaire pour la creation d'un etudiant
        EtudiantRequestDTO expectedEtudiant = EtudiantRequestDTO.builder()
                .apogee("apogee")
                .cne("cne")
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .photo("photo")
                .email("email")
                .password("password")
                .telephone("06 666 666")
                .adresse("adresse")
                .sexe(Femme)
                .build();
        EtudiantResponseDTO savedEtudiant = etudiantService.createEtudiant(expectedEtudiant);

        assertNotNull(savedEtudiant);
        assertEquals("apogee", savedEtudiant.getApogee());
        assertEquals("cne", savedEtudiant.getCne());
        assertEquals("cin", savedEtudiant.getCin());
        assertEquals("nom", savedEtudiant.getNom());
        assertEquals("prenom", savedEtudiant.getPrenom());
        assertEquals("photo", savedEtudiant.getPhoto());
        assertEquals("email", savedEtudiant.getEmail());
        assertEquals("password", savedEtudiant.getPassword());
        assertEquals("06 666 666", savedEtudiant.getTelephone());
        assertEquals("adresse", savedEtudiant.getAdresse());

        assertEquals(Femme, savedEtudiant.getSexe());


    }


    @Test
    void shouldUpdateEtudiant() throws CannotProceedException, EtudiantNotFoundException {
        EtudiantRequestDTO expectedEtudiant = EtudiantRequestDTO.builder()
                .apogee("apogee")
                .cne("cne")
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .photo("photo")
                .email("email")
                .password("password")
                .telephone("06 666 666")
                .adresse("adresse")
                .sexe(Femme)
                .build();
        EtudiantResponseDTO savedEtudiant = etudiantService.createEtudiant(expectedEtudiant);


        expectedEtudiant.setNom("nom updated");

        EtudiantResponseDTO updatedEtudiant = etudiantService.updateEtudiant(savedEtudiant.getApogee(), expectedEtudiant);




        assertNotNull(updatedEtudiant);
        assertEquals(expectedEtudiant.getApogee(), updatedEtudiant.getApogee());
        assertEquals(expectedEtudiant.getCne(), updatedEtudiant.getCne());
        assertEquals(expectedEtudiant.getCin(), updatedEtudiant.getCin());
        assertEquals(expectedEtudiant.getNom(), updatedEtudiant.getNom());
        assertEquals(expectedEtudiant.getPrenom(), updatedEtudiant.getPrenom());
        assertEquals(expectedEtudiant.getPhoto(), updatedEtudiant.getPhoto());
        assertEquals(expectedEtudiant.getEmail(), updatedEtudiant.getEmail());
        assertEquals(expectedEtudiant.getPassword(), updatedEtudiant.getPassword());
        assertEquals(expectedEtudiant.getTelephone(), updatedEtudiant.getTelephone());
        assertEquals(expectedEtudiant.getAdresse(), updatedEtudiant.getAdresse());
        assertEquals(expectedEtudiant.getSexe(), updatedEtudiant.getSexe());


    }

    //test de suppression d'un etudiant
    @Test
    void shouldDeleteEtudiant() throws EtudiantNotFoundException, CannotProceedException {
        EtudiantRequestDTO expectedEtudiant = EtudiantRequestDTO.builder()
                .apogee("apogee")
                .cne("cne")
                .cin("cin")
                .nom("nom")
                .prenom("prenom")
                .photo("photo")
                .email("email")
                .password("password")
                .telephone("06 666 666")
                .adresse("adresse")
                .sexe(Femme)
                .build();
        EtudiantResponseDTO savedEtudiant = etudiantService.createEtudiant(expectedEtudiant);

        etudiantService.deleteEtudiant(savedEtudiant.getApogee());

        assertThrows(EtudiantNotFoundException.class, () -> {
            etudiantService.getEtudiant(savedEtudiant.getApogee());
        });


    }

    //test d'invalidité
    @Test
    void shouldThrowExceptionWhenSearchNonExistant() {
        EtudiantRequestDTO expectedEtudiant = EtudiantRequestDTO.builder()
                .build();

        EtudiantNotFoundException exception = assertThrows(EtudiantNotFoundException.class, () -> {
            etudiantService.createEtudiant(expectedEtudiant);
        });

    }



}
