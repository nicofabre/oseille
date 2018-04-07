package com.comencau.oseille.web;

import com.comencau.oseille.core.ImportService;
import com.comencau.oseille.core.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-27
 */
@Controller
@RequestMapping("import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @RequestMapping
    public String displayImport() {
        return "import";
    }

    @RequestMapping(value = "step1", method = RequestMethod.POST)
    public String uploadFiles(MultipartFile file) throws IOException {
        File f = File.createTempFile("toto", null);
        file.transferTo(f);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            int n = 0;
            String line;
            while ((line = br.readLine()) != null) {
                n++;
                System.out.println(line);
                if (n > 10) {
                    break;
                }
            }
        }
        return "importStep2";
    }

}
