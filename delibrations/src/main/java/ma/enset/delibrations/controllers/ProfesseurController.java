package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.requests.ProfesseurRequestDTO;
import ma.enset.delibrations.dtos.responses.ProfesseurResponseDTO;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.ElementNotFoundException;
import ma.enset.delibrations.exceptions.ProfesseurNotFoundException;
import ma.enset.delibrations.services.ProfesseurService;
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
@Slf4j
@RequestMapping("/api/professeur")
public class ProfesseurController {
    private ProfesseurService professeurService;

    @PostMapping("/import")
    public ResponseEntity<String> importDataFromExcel(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<ProfesseurRequestDTO> dataObjects = new ArrayList<>();
            Iterator<Row> rowIterator = sheet.iterator();
            DataFormatter dataFormatter = new DataFormatter();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                ProfesseurRequestDTO dataObject = new ProfesseurRequestDTO();
                dataObject.setNom(row.getCell(0).getStringCellValue());
                dataObject.setPrenom(row.getCell(1).getStringCellValue());
                dataObject.setCin(row.getCell(2).getStringCellValue());
                dataObject.setAdresse(row.getCell(3).getStringCellValue());
                dataObject.setEmail(row.getCell(4).getStringCellValue());
                dataObject.setTelephone("0"+dataFormatter.formatCellValue(row.getCell(4)));

              // dataObject.setTelephone("0"+row.getCell(5).getNumericCellValue());
                dataObjects.add(dataObject);
            }
            workbook.close();
            for (ProfesseurRequestDTO professeur : dataObjects
            ) {
                professeurService.createProfesseur(professeur);
            }
            return new ResponseEntity<>("Data imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to import data", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ProfesseurNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CannotProceedException e) {
            throw new RuntimeException(e);
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/export")
    public String exportDataToExcel() {
        try {
            List<ProfesseurResponseDTO> dataList = professeurService.getProfesseurs();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("liste_professeurs");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nom");
            headerRow.createCell(1).setCellValue("Prenom");
            headerRow.createCell(2).setCellValue("CIN");
            headerRow.createCell(3).setCellValue("email");
            headerRow.createCell(4).setCellValue("Telephone");
            headerRow.createCell(5).setCellValue("Addresse");

            // Add more header cells for other data fields

            // Populate data rows
            int rowNum = 1;
            for (ProfesseurResponseDTO data : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getNom());
                row.createCell(1).setCellValue(data.getPrenom());
                row.createCell(2).setCellValue(data.getCin());
                row.createCell(3).setCellValue(data.getEmail());
                row.createCell(4).setCellValue(data.getTelephone());
                row.createCell(5).setCellValue(data.getAdresse());
            }

            // Save the workbook to a file
            FileOutputStream fileOutputStream = new FileOutputStream("liste_professeurs.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            return "Data exported successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error exporting data.";
        }
    }

    @GetMapping("/all")
    public List<ProfesseurResponseDTO> getAllProfesseurs() {
        return professeurService.getProfesseurs();
    }

    @GetMapping("/{id}")
    public ProfesseurResponseDTO getProfesseur(@PathVariable String id) throws ProfesseurNotFoundException {
        if (id != null) return professeurService.getProfesseur(id);
        return null;
    }

    @PostMapping("/add")
    public ProfesseurResponseDTO createProfesseur(@RequestBody ProfesseurRequestDTO professeurRequestDTO) throws CannotProceedException, ProfesseurNotFoundException, ElementNotFoundException {
        if (professeurRequestDTO != null) return professeurService.createProfesseur(professeurRequestDTO);
        return null;
    }

    @PutMapping("/{id}")
    public ProfesseurResponseDTO updateProfesseur(@PathVariable String id, @RequestBody ProfesseurRequestDTO professeurRequestDTO) throws ProfesseurNotFoundException, ElementNotFoundException {
        if (professeurRequestDTO != null && id != null)
            return professeurService.updateProfesseur(id, professeurRequestDTO);
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProfesseur(@PathVariable String id) throws ProfesseurNotFoundException {
        if (id != null) {
            professeurService.deleteProfesseur(id);
            return true;

        } else return false;
    }

}
