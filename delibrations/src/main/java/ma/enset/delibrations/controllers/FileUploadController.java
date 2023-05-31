package ma.enset.delibrations.controllers;


import lombok.extern.slf4j.Slf4j;
import ma.enset.delibrations.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@Controller
@Slf4j
@CrossOrigin("*")
public class FileUploadController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload-file")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {

        storageService.store(file);
        Resource loadAsResource = storageService.loadAsResource(file.getOriginalFilename());
        File storeFile = loadAsResource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(storeFile.getAbsolutePath()))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // TODO : add import logic from excel sheet here
                System.out.println(line.split(";")[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

}