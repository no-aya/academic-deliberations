package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.*;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;

import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.services.DepartementService;
import ma.enset.delibrations.services.FiliereService;
import ma.enset.delibrations.services.ModuleService;
import ma.enset.delibrations.services.SemestreService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departement")
public class DepartementController {

    private FiliereController filiereController;
    private SemestreController semestreController;
    private ModuleController moduleController;
    private ElementController elementController;

    private DepartementService departementService;
    private FiliereService filiereService;
    private SemestreService semestreService;
    private ModuleService moduleService;

    @PostMapping("/import")
    public ResponseEntity<String> importDataFromExcel(@RequestParam("file") MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<DepartementRequestDTO> dataObjects = new ArrayList<>();
            List<FiliereRequestDTO> filiereRequestDTOS = new ArrayList<>();
            List<SemestreRequestDTO> semestreRequestDTOS = new ArrayList<>();
            List<ModuleRequestDTO> moduleRequestDTOS = new ArrayList<>();

            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip header row
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                DepartementRequestDTO dataObject = new DepartementRequestDTO();
                dataObject.setCode(row.getCell(0).getStringCellValue());
                dataObject.setIntitule(row.getCell(1).getStringCellValue());

                dataObjects.add(dataObject);
            }

            for (DepartementRequestDTO departement : dataObjects) {
                departementService.createDepartement(departement);
            }


            return ResponseEntity.ok("Les données sont importées avec succès");
        } catch (IOException | CannotProceedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec d'import");
        } catch (FiliereNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RegleCalculNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DepartementNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        @GetMapping("/export")
        public String exportDataToExcel() {
            try {
                List<DepartementResponseDTO> dataList = departementService.getDepartements();

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("liste_des_départements");

                // Create header row
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("CODE_Departement");
                headerRow.createCell(1).setCellValue("Departement_Intitulé");
                headerRow.createCell(2).setCellValue("Code_Filiere");
                headerRow.createCell(3).setCellValue("Filiere_Intitulé");
                headerRow.createCell(4).setCellValue("Code_Semestre");
                headerRow.createCell(5).setCellValue("Semestre_Intitulé");
                headerRow.createCell(6).setCellValue("Code_Module");
                headerRow.createCell(7).setCellValue("Intitulé_Module");
                headerRow.createCell(8).setCellValue("Code_Element_Module");
                headerRow.createCell(9).setCellValue("Intitulé_Element_Module");
                headerRow.createCell(10).setCellValue("Coef");

                // Populate data rows
                int rowNum = 1;
                for (DepartementResponseDTO data : dataList) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(data.getCode());
                    row.createCell(1).setCellValue(data.getIntitule());

                    Long[] filieresID = data.getFilieres();
                    for (Long id:filieresID ) {
                        Row row1 = sheet.createRow(rowNum++);
                        Filiere filiere = filiereService.getFiliere(id);
                        row1.createCell(2).setCellValue(filiere.getCode());
                        row1.createCell(3).setCellValue(filiere.getIntitule());

                        List<Module> modules= filiere.getModules();
                        for (Module module: modules) {
                            Row row2 = sheet.createRow(rowNum++);
                            row2.createCell(6).setCellValue(module.getCode());
                            row2.createCell(7).setCellValue(module.getIntitule());
                            List<Element> elements = module.getElements();
                            for (Element element:elements
                                 ) {
                                Row row3 = sheet.createRow(rowNum++);
                                row3.createCell(8).setCellValue(element.getCode());
                                row3.createCell(9).setCellValue(element.getTitre());
                                row3.createCell(10).setCellValue(element.getPonderation());
                            }
                        }
                    }


                }

                // Save the workbook to a file
                FileOutputStream fileOutputStream = new FileOutputStream("liste_départements.xlsx");
                workbook.write(fileOutputStream);
                fileOutputStream.close();

                return "Data exported successfully!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error exporting data.";
            }
        }




    @GetMapping("/all")
    public List<DepartementResponseDTO> getAllDepartements(){
        return departementService.getDepartements();
    }

    @GetMapping("/{code}")
    public DepartementResponseDTO getDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) return departementService.getDepartement(code);
        return null;
    }

    @PostMapping("/add")
    public DepartementResponseDTO createDepartement(@RequestBody DepartementRequestDTO departementRequestDTO) throws CannotProceedException, DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null) return departementService.createDepartement(departementRequestDTO);
        return null;
    }

    @PutMapping("/{code}")
    public DepartementResponseDTO updateDepartement(@PathVariable String code, @RequestBody DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null && code!=null)
            return departementService.updateDepartement(code, departementRequestDTO);
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) {
            departementService.deleteDepartement(code);
            return true;

        }else return false;
    }
}
