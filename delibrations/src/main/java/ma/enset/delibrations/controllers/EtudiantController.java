package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;

import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.entities.enums.Sexe;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;

import ma.enset.delibrations.entities.exceptions.CannotProceedException;
import ma.enset.delibrations.entities.exceptions.EtudiantNotFoundException;

import ma.enset.delibrations.services.EtudiantService;
import ma.enset.delibrations.services.FiliereService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/etudiant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EtudiantController {
    private EtudiantService etudiantService;
    private FiliereService filiereService;


    @PostMapping("/import")
    public ResponseEntity<String> importDataFromExcel(@RequestParam("file") MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            List<EtudiantRequestDTO> dataObjects = new ArrayList<>();
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next(); // Skip header row
            }
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                EtudiantRequestDTO dataObject = new EtudiantRequestDTO();
                dataObject.setApogee(String.valueOf((int) row.getCell(0).getNumericCellValue()));
                dataObject.setCne(row.getCell(1).getStringCellValue());
                dataObject.setCin(row.getCell(2).getStringCellValue());
                dataObject.setNom(row.getCell(3).getStringCellValue());
                dataObject.setPrenom(row.getCell(4).getStringCellValue());

                // Date de Naissance
                double dateStringFormat = row.getCell(12).getNumericCellValue();
                Date dateNaissance = DateUtil.getJavaDate(dateStringFormat);
               dataObject.setDateNaissance(dateNaissance);

                // Adress Concatenation
                StringBuilder adresse = new StringBuilder();
                for (int i = 13; i <= 15; i++) {
                    Cell cell = row.getCell(i);
                    if (cell != null) {
                        adresse.append(cell.getStringCellValue()).append(" ");
                    }
                }
                dataObject.setAdresse(adresse.toString().trim());

                String sexe = row.getCell(11).getStringCellValue();
                dataObject.setSexe(sexe.equals("F") ? Sexe.Femme : Sexe.Homme);

                String filiereCode = row.getCell(7).getStringCellValue();

                FiliereResponseDTO filiereResponseDTO = filiereService.getFiliere(filiereCode);
                dataObject.setFiliereID(filiereResponseDTO.getId());

                dataObjects.add(dataObject);
            }

            for (EtudiantRequestDTO etudiant : dataObjects) {
                etudiantService.createEtudiant(etudiant);
            }

            return ResponseEntity.ok("Les données sont importées avec succès");
        } catch (IOException | CannotProceedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec d'import");
        } catch (FiliereNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/export")
    public String exportDataToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("liste_des_etudiants");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("COD_ETU");
            headerRow.createCell(1).setCellValue("COD_NNE_IND");
            headerRow.createCell(2).setCellValue("CIN_IND");
            headerRow.createCell(3).setCellValue("LIB_NOM_PAT_IND");
            headerRow.createCell(4).setCellValue("LIB_PR1_IND");
            headerRow.createCell(5).setCellValue("COD_DIP");
            headerRow.createCell(6).setCellValue("LIB_DIP");
            headerRow.createCell(7).setCellValue("COD_SEX_ETU");
            headerRow.createCell(8).setCellValue("DATE_NAI_IND");
            headerRow.createCell(9).setCellValue("LIB_AD");

            // Add more header cells for other data fields

            List<EtudiantResponseDTO> dataList = etudiantService.getEtudiants();

            // Populate data rows
            int rowNum = 1;
            for (EtudiantResponseDTO data : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getApogee());
                row.createCell(1).setCellValue(data.getCne());
                row.createCell(2).setCellValue(data.getCin());
                row.createCell(3).setCellValue(data.getNom());
                row.createCell(4).setCellValue(data.getPrenom());

                Filiere filiere = filiereService.getFiliere(data.getFiliere());
                row.createCell(5).setCellValue(filiere.getCode());
                row.createCell(6).setCellValue(filiere.getIntitule());

                row.createCell(7).setCellValue(data.getSexe().toString());
                row.createCell(8).setCellValue(data.getDateNaissance());
                row.createCell(9).setCellValue(data.getAdresse());
                // Add more cells for other data fields
            }

            // Save the workbook to a file
            String fileName = "liste_etudiants.xlsx";
            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                workbook.write(fileOutputStream);
            }

            return "Data exported successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error exporting data.";
        }
    }

    @GetMapping("/all")
    public List<EtudiantResponseDTO> getAllEtudiants() {
        return etudiantService.getEtudiants();
    }

    @GetMapping("/{code}")
    public EtudiantResponseDTO getEtudiant(@PathVariable String code) throws EtudiantNotFoundException {
        if (code != null) return etudiantService.getEtudiant(code);
        return null;
    }

    @PostMapping("/add")
    public EtudiantResponseDTO createEtudiant(@RequestBody EtudiantRequestDTO etudiantRequestDTO) throws CannotProceedException {
        if (etudiantRequestDTO != null) return etudiantService.createEtudiant(etudiantRequestDTO);
        return null;
    }

    @PutMapping("/{code}")
    public EtudiantResponseDTO updateEtudiant(@PathVariable String code, @RequestBody EtudiantRequestDTO etudiantRequestDTO) throws EtudiantNotFoundException, CannotProceedException {
        if (etudiantRequestDTO != null && code != null) return etudiantService.updateEtudiant(code, etudiantRequestDTO);
        return null;
    }

    @DeleteMapping("/{code}")
    public void deleteEtudiant(@PathVariable String code) throws EtudiantNotFoundException {
        if (code != null)
            etudiantService.deleteEtudiant(code);

    }

}
