package com.comencau.oseille.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-27
 */
@Controller
public class WelcomeController {

    @RequestMapping("welcome")
    public String displayWelcome() {
        return "welcome";
    }

}
