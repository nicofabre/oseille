package com.comencau.oseille.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.comencau.oseille.core.ImportReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.comencau.oseille.core.ImportService;

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
    public String uploadFiles(Model model, MultipartFile file) throws IOException {
        File f = File.createTempFile("toto", null);
        file.transferTo(f);
        ImportReport report = importService.parseINGFile(f);
        model.addAttribute("report", report);
        return "importStep2";
    }

}
