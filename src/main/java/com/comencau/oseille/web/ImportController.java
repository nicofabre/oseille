package com.comencau.oseille.web;

import com.comencau.oseille.core.ImportService;
import com.comencau.oseille.core.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(method = RequestMethod.POST)
    public String uploadFiles() {


        return "importStep2";
    }

}
