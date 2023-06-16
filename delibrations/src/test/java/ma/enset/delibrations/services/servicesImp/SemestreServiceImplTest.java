package ma.enset.delibrations.services.servicesImp;

import ma.enset.delibrations.dtos.mappers.SemestreMapper;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.SemestreResponseDTO;
import ma.enset.delibrations.exceptions.AnneeUnivNotFoundException;
import ma.enset.delibrations.exceptions.NoteSemestreNotFoundException;
import ma.enset.delibrations.exceptions.SemestreNotFoundException;
import ma.enset.delibrations.services.SemestreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.CannotProceedException;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class SemestreServiceImplTest {

    @Autowired
    SemestreService semestreService;
    @Test
    void createSemestre() throws CannotProceedException, NoteSemestreNotFoundException {
        SemestreRequestDTO expectedSemestre = SemestreRequestDTO.builder()
                .code("code")
                .libelle("libelle")
                .build();

        SemestreResponseDTO savedSemestre = semestreService.createSemestre(expectedSemestre);

        assertNotNull(savedSemestre);
        assertEquals(expectedSemestre.getCode(), savedSemestre.getCode());
        assertEquals(expectedSemestre.getLibelle(), savedSemestre.getLibelle());

    }

    @Test
    void updateSemestre() throws CannotProceedException, NoteSemestreNotFoundException, AnneeUnivNotFoundException, ma.enset.delibrations.exceptions.CannotProceedException, SemestreNotFoundException {
        SemestreRequestDTO expectedSemestre = SemestreRequestDTO.builder()
                .code("code")
                .libelle("libelle")
                .build();

        semestreService.createSemestre(expectedSemestre);
        expectedSemestre.setLibelle("new libelle");

        SemestreResponseDTO updatedSemestre = semestreService.updateSemestre(expectedSemestre.getCode(), expectedSemestre);


        assertNotNull(updatedSemestre);
        assertEquals(expectedSemestre.getCode(), updatedSemestre.getCode());
        assertEquals(expectedSemestre.getLibelle(), updatedSemestre.getLibelle());


    }

    @Test
    void getSemestre() throws CannotProceedException, NoteSemestreNotFoundException, SemestreNotFoundException {
        SemestreRequestDTO expectedSemestre = SemestreRequestDTO.builder()
                .code("code")
                .libelle("libelle")
                .build();

        SemestreResponseDTO savedSemestre = semestreService.createSemestre(expectedSemestre);

        SemestreResponseDTO foundSemestre = semestreService.getSemestre(savedSemestre.getCode());

        assertNotNull(foundSemestre);
        assertEquals(savedSemestre.getCode(), foundSemestre.getCode());
        assertEquals(savedSemestre.getLibelle(), foundSemestre.getLibelle());





    }

    @Test
    void deleteSemestre() throws CannotProceedException, NoteSemestreNotFoundException, SemestreNotFoundException {
        SemestreRequestDTO expectedSemestre = SemestreRequestDTO.builder()
                .code("code")
                .libelle("libelle")
                .build();

        SemestreResponseDTO savedSemestre = semestreService.createSemestre(expectedSemestre);

        semestreService.deleteSemestre(savedSemestre.getCode());

        assertThrows(SemestreNotFoundException.class, () -> semestreService.getSemestre(savedSemestre.getCode()));

    }
}
