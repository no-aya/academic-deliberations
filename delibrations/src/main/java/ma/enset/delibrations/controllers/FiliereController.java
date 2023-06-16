package ma.enset.delibrations.controllers;

import lombok.AllArgsConstructor;
import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.responses.DepartementResponseDTO;
import ma.enset.delibrations.dtos.responses.FiliereResponseDTO;

import ma.enset.delibrations.entities.Filiere;
import ma.enset.delibrations.exceptions.CannotProceedException;
import ma.enset.delibrations.exceptions.DepartementNotFoundException;
import ma.enset.delibrations.exceptions.FiliereNotFoundException;
import ma.enset.delibrations.exceptions.RegleCalculNotFoundException;
import ma.enset.delibrations.entities.exceptions.*;
import ma.enset.delibrations.services.FiliereService;
import ma.enset.delibrations.services.ModuleService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filiere")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FiliereController {
    private FiliereService filiereService;
    private ModuleService moduleService;
/*
    @GetMapping("/export")
    public String exportDataToExcel() {
        try {
            List<FiliereResponseDTO> dataList = filiereService.getFilieres();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("liste_des_filières");

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
            for (FiliereResponseDTO data : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.);
                row.createCell(1).setCellValue(data.getCne());
                row.createCell(2).setCellValue(data.getCin());
                row.createCell(3).setCellValue(data.getNom());
                row.createCell(4).setCellValue(data.getPrenom());
                row.createCell(5).setCellValue(" ");
                row.createCell(6).setCellValue("data.getCin()");
                row.createCell(7).setCellValue(data.getSexe().toString());
                row.createCell(8).setCellValue(data.getDateNaissance());
                row.createCell(9).setCellValue(data.getAdresse());
            }

            // Save the workbook to a file
            FileOutputStream fileOutputStream = new FileOutputStream("liste_filières.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            return "Data exported successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error exporting data.";
        }
    }
    */
    @GetMapping("/all")
    public List<FiliereResponseDTO> getAllFilieres(){
        return filiereService.getFilieres();
    }


    @GetMapping("/{code}")
    public FiliereResponseDTO getFiliere(@PathVariable String code) throws FiliereNotFoundException {
        if (code!=null) return filiereService.getFiliere(code);
        return null;
    }

    @PostMapping("/add")
    public FiliereResponseDTO createFiliere(@RequestBody FiliereRequestDTO filiereRequestDTO) throws DepartementNotFoundException, FiliereNotFoundException, CannotProceedException, RegleCalculNotFoundException {
        return filiereService.createFiliere(filiereRequestDTO);
    }

    @PutMapping("/{code}")
    public FiliereResponseDTO updateFiliere(@PathVariable String code, @RequestBody FiliereRequestDTO filiereRequestDTO) throws FiliereNotFoundException, DepartementNotFoundException, RegleCalculNotFoundException {
        if (code!=null) {
            filiereRequestDTO.setCode(code);
            return filiereService.updateFiliere(filiereRequestDTO);
        }
        return null;
    }

    @DeleteMapping("/{code}")
    public Boolean deleteFiliere(@PathVariable String code) throws FiliereNotFoundException {
        if(code!=null) {
            filiereService.deleteFiliere(code);
            return true;
        }
        return false;
    }

    @GetMapping
    public List<FiliereResponseDTO> getFiliere(@RequestParam Long idProf, @RequestParam Long idDept,@RequestParam String libelS) throws FiliereNotFoundException, ModuleNotFoundException {
        if (idProf!=null && idDept!=null  && libelS!=null ) return filiereService.getFiliereWithDeptAndProf(idProf, idDept,libelS);
        return null;
    }

}
