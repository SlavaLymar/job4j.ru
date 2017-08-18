package ru.yalymar.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ExitController {

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:signin.do";
    }

}
