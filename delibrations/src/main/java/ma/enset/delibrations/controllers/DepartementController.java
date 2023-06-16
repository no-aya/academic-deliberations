package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.*;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.services.DepartementService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departements")
@CrossOrigin("*")
public class DepartementController {

    private DepartementService departementService;
    private FiliereService filiereService;
    private SemestreService semestreService;
    private ModuleService moduleService;
    private ElementService elementService;

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
            //pour les departements
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                DepartementRequestDTO dataObject = new DepartementRequestDTO();
                String depCode= row.getCell(0).getStringCellValue();
                if (!depCode.isBlank()) {
                    dataObject.setCode(row.getCell(0).getStringCellValue());
                    dataObject.setIntitule(row.getCell(1).getStringCellValue());
                    departementService.createDepartement(dataObject);
                }


            }

            //pour les filieres
            rowIterator = sheet.iterator();
            rowIterator.next();
            List<String> depCodes = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FiliereRequestDTO dataObject = new FiliereRequestDTO();
                String filCode= row.getCell(2).getStringCellValue();
                if(!filCode.isBlank()) {
                    dataObject.setCode(row.getCell(2).getStringCellValue());
                    dataObject.setIntitule(row.getCell(3).getStringCellValue());
                    String codeDep = row.getCell(0).getStringCellValue();
                    if (!codeDep.isBlank()) depCodes.add(codeDep);
                    DepartementResponseDTO departement = departementService.getDepartement(depCodes.get(depCodes.size() - 1));
                    dataObject.setDepartementId(departement.getId());
                    filiereService.createFiliere(dataObject);
                }

            }

            //pour les semestres
            rowIterator = sheet.iterator();
            rowIterator.next();
            List<String> filCodes = new ArrayList<>(); // list qui contient les codes des filieres
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                SemestreRequestDTO dataObject = new SemestreRequestDTO();
                String semCode= row.getCell(4).getStringCellValue();
                if(!semCode.isBlank()) {
                    dataObject.setCode(row.getCell(4).getStringCellValue());
                    dataObject.setLibelle(row.getCell(5).getStringCellValue());
                    String codeFil = row.getCell(2).getStringCellValue();
                    if (!codeFil.isBlank()) filCodes.add(codeFil);
                    FiliereResponseDTO filiere = filiereService.getFiliere(filCodes.get(filCodes.size() - 1));
                    dataObject.setFiliereID(filiere.getId());
                    semestreService.createSemestre(dataObject);
                }

            }
            //pour les modules
            rowIterator = sheet.iterator();
            rowIterator.next();
            List<String> semCodes = new ArrayList<>(); // list qui contient les codes des semestres
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ModuleRequestDTO dataObject = new ModuleRequestDTO();
                String moduleCode= row.getCell(6).getStringCellValue();
                if(!moduleCode.isBlank()) {
                    dataObject.setCode(moduleCode);
                    dataObject.setIntitule(row.getCell(7).getStringCellValue());
                    String codeSemstre = row.getCell(4).getStringCellValue();
                    if (!codeSemstre.isBlank()) semCodes.add(codeSemstre);
                    SemestreResponseDTO semestre = semestreService.getSemestre(semCodes.get(semCodes.size() - 1));
                    dataObject.setSemestreId(semestre.getId());
                    moduleService.createModule(dataObject);
                }

            }

            //pour les élements de module
            rowIterator = sheet.iterator();
            rowIterator.next();
            List<String> modCodes = new ArrayList<>(); // list qui contient les codes des modules
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ElementRequestDTO dataObject = new ElementRequestDTO();
                String elementCode= row.getCell(8).getStringCellValue();
                if(!elementCode.isBlank()) {
                    dataObject.setCode(elementCode);
                    dataObject.setTitre(row.getCell(9).getStringCellValue());
                    dataObject.setCoef((float) row.getCell(10).getNumericCellValue());
                    String codeModule = row.getCell(6).getStringCellValue();
                    if (!codeModule.isBlank()) modCodes.add(codeModule);
                    ModuleResponseDTO module = moduleService.getModule(modCodes.get(modCodes.size() - 1));
                    dataObject.setModuleId(module.getId());
                    elementService.addElement(dataObject);
                }

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
        } catch (javax.naming.CannotProceedException e) {
            throw new RuntimeException(e);
        } catch (NoteSemestreNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SemestreNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoteModuleNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ModuleNotFoundException e) {
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
                                row3.createCell(10).setCellValue(element.getCoef());
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

    @GetMapping("/departement/{code}")
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
    public DepartementResponseDTO updateDepartement(@PathVariable Long id, @RequestBody DepartementRequestDTO departementRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, RegleCalculNotFoundException {
        if(departementRequestDTO!=null && id!=null)
            return departementService.updateDepartement(id, departementRequestDTO);
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteDepartement(@PathVariable String code) throws DepartementNotFoundException {
        if(code!=null) {
            departementService.deleteDepartement(code);
            return true;

        }else return false;
    }


    @GetMapping
    public List<DepartementResponseDTO> getDepartementsByProf(@RequestParam Long id,@RequestParam String codeAnnee,@RequestParam String libelS) throws ProfesseurNotFoundException, ModuleNotFoundException, FiliereNotFoundException, DepartementNotFoundException {
        if(id!=null && codeAnnee!=null && libelS!=null) {
            return  departementService.getDepartementsByProf(id, codeAnnee, libelS);
        }else return null;
    }
 
}
