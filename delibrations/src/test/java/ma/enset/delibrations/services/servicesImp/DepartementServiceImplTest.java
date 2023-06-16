package ma.enset.delibrations.services.servicesImp;

import ma.enset.delibrations.dtos.requests.DepartementRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.services.DepartementService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DepartementServiceImplTest {

    @Autowired
    DepartementService departementService;
    @Test
    void createDepartement() throws RegleCalculNotFoundException, DepartementNotFoundException, FiliereNotFoundException, CannotProceedException {
        DepartementRequestDTO expectedDepartement = DepartementRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        DepartementResponseDTO savedDepartement = departementService.createDepartement(expectedDepartement);

        assertNotNull(savedDepartement);
        assertEquals(expectedDepartement.getCode(), savedDepartement.getCode());
        assertEquals(expectedDepartement.getIntitule(), savedDepartement.getIntitule());


    }

    @Test
    void updateDepartement() throws RegleCalculNotFoundException, DepartementNotFoundException, FiliereNotFoundException, CannotProceedException {
        DepartementRequestDTO expectedDepartement = DepartementRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        DepartementResponseDTO savedDepartement = departementService.createDepartement(expectedDepartement);
        expectedDepartement.setIntitule("new intitule");

        DepartementResponseDTO updatedDepartement = departementService.updateDepartement(expectedDepartement.getCode(), expectedDepartement);

        assertNotNull(updatedDepartement);
        assertEquals(expectedDepartement.getCode(), updatedDepartement.getCode());
        assertEquals(expectedDepartement.getIntitule(), updatedDepartement.getIntitule());

    }

    @Test
    void getDepartement() throws DepartementNotFoundException, RegleCalculNotFoundException, FiliereNotFoundException, CannotProceedException {
        DepartementRequestDTO expectedDepartement = DepartementRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        DepartementResponseDTO savedDepartement = departementService.createDepartement(expectedDepartement);
        DepartementResponseDTO foundDepartement = departementService.getDepartement(savedDepartement.getCode());

        assertNotNull(foundDepartement);
        assertEquals(expectedDepartement.getCode(), foundDepartement.getCode());
        assertEquals(expectedDepartement.getIntitule(), foundDepartement.getIntitule());

    }


    @Test
    void deleteDepartement() throws RegleCalculNotFoundException, DepartementNotFoundException, FiliereNotFoundException, CannotProceedException {
        DepartementRequestDTO expectedDepartement = DepartementRequestDTO.builder()
                .code("code")
                .intitule("intitule")
                .build();

        DepartementResponseDTO savedDepartement = departementService.createDepartement(expectedDepartement);
        departementService.deleteDepartement(savedDepartement.getCode());
        assertThrows(DepartementNotFoundException.class, () -> departementService.getDepartement(savedDepartement.getCode()));


    }
}
