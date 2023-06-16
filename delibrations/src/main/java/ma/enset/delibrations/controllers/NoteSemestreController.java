package ma.enset.delibrations.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.dtos.requests.AnneeUnivRequestDTO;
import ma.enset.delibrations.dtos.requests.FiliereRequestDTO;
import ma.enset.delibrations.dtos.requests.NoteSemestreRequestDTO;
import ma.enset.delibrations.dtos.requests.SemestreRequestDTO;
import ma.enset.delibrations.dtos.responses.EtudiantResponseDTO;
import ma.enset.delibrations.dtos.responses.NoteSemestreResponseDTO;
import ma.enset.delibrations.entities.Element;
import ma.enset.delibrations.entities.Module;
import ma.enset.delibrations.exceptions.*;
import ma.enset.delibrations.services.EtudiantService;
import ma.enset.delibrations.services.NoteElementService;
import ma.enset.delibrations.services.NoteModuleService;

import ma.enset.delibrations.entities.exceptions.*;
import ma.enset.delibrations.services.NoteSemestreService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/note-semestre")
public class NoteSemestreController {

    private NoteSemestreService noteSemestreService;
    private NoteModuleService noteModuleService;
    private NoteElementService noteElementService;
    private EtudiantService etudiantService;
/*
    @GetMapping("/export")
    public String exportDataToExcel(@RequestParam("anneUniv")AnneeUnivRequestDTO anneeUnivRequestDTO,
                                    @RequestParam("semestre")SemestreRequestDTO semestreRequestDTO,
                                    @RequestParam("filiere")FiliereRequestDTO filiereRequestDTO) {
        try {
            int initalRow = 17;
            int initalCol = 4;

            FileInputStream theSavedFile = null;
            try {
                theSavedFile = new FileInputStream(new File(new File("").getAbsolutePath() + "/templates/notes_template.xls"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            HSSFWorkbook workbook = null;

            try {
                workbook = new HSSFWorkbook(theSavedFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            HSSFSheet sheet = workbook.getSheetAt(0);
            sheet.setGridsPrinted(true);

            CellStyle style = workbook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.CENTER);

            CellStyle wrap = workbook.createCellStyle();
            wrap.setWrapText(true);
            wrap.setVerticalAlignment(VerticalAlignment.CENTER);
            wrap.setAlignment(HorizontalAlignment.CENTER);

            //get etudiants by filiere, annee universitaire , semestre, session
            List<EtudiantResponseDTO> etudiantResponseDTOS = etudiantService.getEtudiants();
            List<Module> elementsModules = new ArrayList<>();

            int headStart = 4;

            List<Module> modules = output.getStudents().get(0).getModules();

            Row headRow = sheet.createRow(13);
            Row sessionRow = sheet.createRow(14);
            Row coefRow = sheet.createRow(15);
            Cell cell01 = coefRow.createCell(0);
            cell01.setCellValue("Etudiant");
            cell01.setCellStyle(style);

            Cell cell02 = coefRow.createCell(3);
            cell02.setCellValue("Admissibilité");
            cell02.setCellStyle(style);

            Row noteAndResltRow = sheet.createRow(16);

            headRow.setHeight((short) 1000);

            Cell cell1 = noteAndResltRow.createCell(0);
            cell1.setCellValue("Numero");
            cell1.setCellStyle(style);

            Cell cell2 = noteAndResltRow.createCell(1);
            cell2.setCellValue("Nom");
            cell2.setCellStyle(style);


            Cell cell3 = noteAndResltRow.createCell(2);
            cell3.setCellValue("Prenom");
            cell3.setCellStyle(style);

            Cell cell4 = noteAndResltRow.createCell(3);
            cell4.setCellValue("Naissance");
            cell4.setCellStyle(style);


            for (int i = 0; i < modules.size(); i++) {
                int startmerge = headStart;


                List<Element> elements = modules.get(i).getElements();
                if (genererAvecElements) {
                    for (int j = 0; j < elements.size(); j++) {
                        startmerge = headStart;

                        Cell note = noteAndResltRow.createCell(headStart);
                        note.setCellValue("Note");
                        note.setCellStyle(style);

                        Cell cell03 = noteAndResltRow.createCell(headStart + 1);
                        cell03.setCellValue("Bareme");
                        cell03.setCellStyle(style);

                        Cell cell04 = sessionRow.createCell(headStart);
                        cell04.setCellValue(1);
                        cell04.setCellStyle(style);

                        Cell cell05 = sessionRow.createCell(headStart + 1);
                        cell05.setCellValue(1);
                        cell05.setCellStyle(style);

                        Cell elemCell = headRow.createCell(headStart++);
                        elemCell.setCellStyle(wrap);
                        elemCell.setCellValue(elements.get(j).getCode() + " -\n " + elements.get(j).getTitre());

                        elemCell.setCellStyle(style);
                        headStart++;
                        sheet.addMergedRegion(new CellRangeAddress(13, 13, startmerge, startmerge + 1));
                    }
                }
            }

            for (int i = 0; i < lignes.size(); i++) {
                int currentCellIndex = initalCol;

                Row row = sheet.createRow(initalRow + i);

                Cell cell06 = row.createCell(0);
                cell06.setCellValue(String.valueOf(lignes.get(i).getNumero()));
                cell06.setCellStyle(style);

                Cell cell07 = row.createCell(1);
                cell07.setCellValue(lignes.get(i).getNom());
                cell07.setCellStyle(style);
                sheet.setColumnWidth(cell07.getColumnIndex(), 5000);


                Cell cell08 = row.createCell(2);
                cell08.setCellValue(lignes.get(i).getPrenom());
                cell08.setCellStyle(style);
                sheet.setColumnWidth(cell08.getColumnIndex(), 5000);


                Cell cell09 = row.createCell(3);
                cell09.setCellValue(lignes.get(i).getDateNaissance());
                cell09.setCellStyle(style);

                Cell cellNoteSemestre = row.createCell(4);
                cellNoteSemestre.setCellValue(String.valueOf(lignes.get(i).getNoteSemestre()));
                cellNoteSemestre.setCellStyle(style);

                Cell cellResultatSemestre = row.createCell(5);
                cellResultatSemestre.setCellValue(lignes.get(i).getResultatSemestre());
                cellResultatSemestre.setCellStyle(style);

                List<Module> etudiantModules = lignes.get(i).getModules();

                for (Module etudiantModule : etudiantModules) {

                    if (genererAvecElements) {
                        List<Element> etudiantElementsModules = etudiantModule.getElements();

                        for (Element etudiantElementsModule : etudiantElementsModules) {

                            Cell cellNoteElement = row.createCell(currentCellIndex++);
                            cellNoteElement.setCellValue(String.valueOf(etudiantElementsModule.getNote()));

                            Cell cellBaremeElement = row.createCell(currentCellIndex++);
                            cellBaremeElement.setCellValue(20);

                            cellBaremeElement.setCellStyle(style);
                            cellNoteElement.setCellStyle(style);
                        }
                    }
                }

            }

            Map<String, Object> data = new HashMap<>();

            try {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
                String name = "S1S1-" + dateFormat.format(new Date()) + ".xls";
                try (FileOutputStream out = new FileOutputStream(new File("files/" + name))) {
                    workbook.write(out);
                    workbook.close();
                    data.put("status", 1);
                    data.put("name", name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return data;
        }

        public boolean isNumeric(String strNum) {
            if (strNum == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(strNum);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }

        public void changeCellBackgroundColor(Cell cell) {
            CellStyle cellStyle = cell.getCellStyle();
            if (cellStyle == null) {
                cellStyle = cell.getSheet().getWorkbook().createCellStyle();
            }
            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(cellStyle);
        }

        public String getCellValueAsString(Cell cell) {
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue().trim();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                return String.valueOf(cell.getNumericCellValue()).trim();
            } else if (cell.getCellType() == CellType.FORMULA) {
                if (cell.getCachedFormulaResultType() == CellType.NUMERIC) {
                    return String.valueOf(cell.getNumericCellValue()).trim();
                } else if (cell.getCachedFormulaResultType() == CellType.STRING) {
                    return cell.getStringCellValue().trim();
                }
            }
            return "null";
        }

        public Map<String, String> getSheetInformation(HSSFSheet sheet) {
            Map<String, String> sheetInformation = new HashMap<>();
            Iterator<Row> rowIterator = sheet.iterator();
            int rowCounter = 1;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellCounter = 1;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = getCellValueAsString(cell);

                    if (rowCounter == 3 && cellCounter == 7) {
                        sheetInformation.put("year", cellValue);
                    }


                    cellCounter += 1;
                }
                rowCounter++;
            }
            return sheetInformation;
        }


 */
        @GetMapping("/all")
    public List<NoteSemestreResponseDTO> getAllNoteSemestres(){
        return noteSemestreService.getAllNotesSemestre();
    }

    @GetMapping("/{id}")
    public NoteSemestreResponseDTO getNoteSemestre(@PathVariable Long id) throws NoteSemestreNotFoundException {
        if(id!=null) return noteSemestreService.getNoteSemestre(id);
        return null;
    }

    @PostMapping("/add")
    public NoteSemestreResponseDTO createNoteSemestre(@RequestBody NoteSemestreRequestDTO noteSemestreRequestDTO) throws SemestreNotFoundException {
        return noteSemestreService.addNoteSemestre(noteSemestreRequestDTO);
    }

    @PutMapping("/{id}")
    public NoteSemestreResponseDTO updateNoteSemestre(@PathVariable Long id, @RequestBody NoteSemestreRequestDTO noteSemestreRequestDTO) throws NoteSemestreNotFoundException, ProfesseurNotFoundException, CannotProceedException, ElementNotFoundException, SemestreNotFoundException {
        if(id!=null) {
            noteSemestreRequestDTO.setId(id);
            return noteSemestreService.updateNoteSemestre(noteSemestreRequestDTO);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteNoteSemestre(@PathVariable Long id) throws NoteSemestreNotFoundException {
        if(id!=null) {
            noteSemestreService.deleteNoteSemestre(id);
            return true;
        }
        return false;
    }

}

