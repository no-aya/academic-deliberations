package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.EtudiantRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;
import ma.enset.delibrations.entities.enums.Sexe;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.EtudiantNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.services.EtudiantService;
import ma.enset.delibrations.services.FiliereService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/etudiant")
public class EtudiantController {
    private EtudiantService etudiantService;
    private FiliereService filiereService;



    @PostMapping("/import")
    public ResponseEntity<String> importDatafromExcel(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List<EtudiantRequestDTO> dataObjects = new ArrayList<>();
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                EtudiantRequestDTO dataObject = new EtudiantRequestDTO();
                dataObject.setApogee(String.valueOf(row.getCell(0).getNumericCellValue()));
                dataObject.setCne(row.getCell(1).getStringCellValue());
                dataObject.setCin(row.getCell(2).getStringCellValue());
                dataObject.setNom(row.getCell(3).getStringCellValue());
                dataObject.setPrenom(row.getCell(4).getStringCellValue());

                // Date de Naissance
                double dateStringFormat = row.getCell(12).getNumericCellValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                //Date dateNaissance = dateFormat.parse(dateStringFormat);
                //dataObject.setDateNaissance(dateNaissance);

                // Concatenation d'addresse
                dataObject.setAdresse(row.getCell(13).getStringCellValue() + " " + row.getCell(14).getStringCellValue() + " " + row.getCell(15).getStringCellValue());
                String sexe = row.getCell(11).getStringCellValue();

                dataObject.setSexe(sexe.equals("F") ? Sexe.Femme : Sexe.Homme);

                // Filiere
                String filiereCode = row.getCell(9).getStringCellValue();
                System.out.println(filiereCode);
                FiliereResponseDTO filiereResponseDTO = filiereService.getFiliere(filiereCode);
                dataObject.setFiliereID(filiereResponseDTO.getId());
                dataObjects.add(dataObject);
            }
            workbook.close();

            for (EtudiantRequestDTO etudiant : dataObjects) {
                etudiantService.createEtudiant(etudiant);
            }

            return new ResponseEntity<>("Les données sont importées avec succes", HttpStatus.OK);
        } catch (IOException | CannotProceedException e) {
            e.printStackTrace();
            return new ResponseEntity<>("echec d'import", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FiliereNotFoundException e) {
            throw new RuntimeException(e);

        }
    }


    @GetMapping("/export")
    public String exportDataToExcel() {
        try {
            List<EtudiantResponseDTO> dataList = etudiantService.getEtudiants();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("liste_des_etudiants");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("COD_ETU");
            headerRow.createCell(1).setCellValue("COD_NNE_IND");
            headerRow.createCell(2).setCellValue("CIN_IND");
            headerRow.createCell(3).setCellValue("LIB_NOM_PAT_IND");
            headerRow.createCell(4).setCellValue("LIB_PR1_IND");
            headerRow.createCell(5).setCellValue("COD_ETP");
            headerRow.createCell(6).setCellValue("COD_DIP");
            headerRow.createCell(7).setCellValue("COD_SEX_ETU");
            headerRow.createCell(8).setCellValue("DATE_NAI_IND");
            headerRow.createCell(9).setCellValue("LIB_AD");


            // Add more header cells for other data fields

            // Populate data rows
            int rowNum = 1;
            for (EtudiantResponseDTO data : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getApogee());
                row.createCell(1).setCellValue(data.getCne());
                row.createCell(2).setCellValue(data.getCin());
                row.createCell(3).setCellValue(data.getNom());
                row.createCell(4).setCellValue(data.getPrenom());
                row.createCell(5).setCellValue(data.getFiliere());
                row.createCell(6).setCellValue("data.getCin()");
                row.createCell(7).setCellValue(data.getSexe().toString());
                row.createCell(8).setCellValue(data.getDateNaissance());
                row.createCell(9).setCellValue(data.getAdresse());
                // Add more cells for other data fields
            }

            // Save the workbook to a file
            FileOutputStream fileOutputStream = new FileOutputStream("liste_etudiants.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

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
