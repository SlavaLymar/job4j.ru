package ru.yalymar.mvc.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/signin")
public class SigninController {

    public String showLoginForm() {
        return "login";
    }


}