package ma.enset.delibrations.services.servicesImp;

import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.services.FiliereService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FiliereServiceImplTest {

    @Autowired
    FiliereService filiereService;

    @Test
    void createFiliere() throws RegleCalculNotFoundException, FiliereNotFoundException, CannotProceedException {

        FiliereRequestDTO expectedFiliere = FiliereRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        FiliereResponseDTO savedFiliere = filiereService.createFiliere(expectedFiliere);

        assertNotNull(savedFiliere);
        assertEquals(expectedFiliere.getCode(), savedFiliere.getCode());
        assertEquals(expectedFiliere.getIntitule(), savedFiliere.getIntitule());

    }

    @Test
    void updateFiliere() throws RegleCalculNotFoundException, FiliereNotFoundException, CannotProceedException, DepartementNotFoundException {
        FiliereRequestDTO expectedFiliere = FiliereRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();
        FiliereResponseDTO savedFiliere = filiereService.createFiliere(expectedFiliere);
        expectedFiliere.setIntitule("new intitule");

        FiliereResponseDTO updatedFiliere = filiereService.updateFiliere(expectedFiliere);

        assertNotNull(updatedFiliere);
        assertEquals(expectedFiliere.getCode(), updatedFiliere.getCode());
        assertEquals(expectedFiliere.getIntitule(), updatedFiliere.getIntitule());

    }

    @Test
    void getFiliere() throws RegleCalculNotFoundException, FiliereNotFoundException, CannotProceedException {
        FiliereRequestDTO expectedFiliere = FiliereRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        FiliereResponseDTO savedFiliere = filiereService.createFiliere(expectedFiliere);

        FiliereResponseDTO foundFiliere = filiereService.getFiliere(expectedFiliere.getCode());

        assertNotNull(foundFiliere);
        assertEquals(expectedFiliere.getCode(), foundFiliere.getCode());
        assertEquals(expectedFiliere.getIntitule(), foundFiliere.getIntitule());


    }

    //Soft delete is false !!!!!
    @Test
    void deleteFiliere() throws RegleCalculNotFoundException, FiliereNotFoundException, CannotProceedException {
        FiliereRequestDTO expectedFiliere = FiliereRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        FiliereResponseDTO savedFiliere = filiereService.createFiliere(expectedFiliere);

        filiereService.deleteFiliere(expectedFiliere.getCode());

        assertThrows(FiliereNotFoundException.class, () -> filiereService.getFiliere(expectedFiliere.getCode()));
    }

}
