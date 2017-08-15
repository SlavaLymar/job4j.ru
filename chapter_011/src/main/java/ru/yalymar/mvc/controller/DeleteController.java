package ru.yalymar.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yalymar.mvc.model.dao.DAOFactory;

@Controller
@RequestMapping(value = "/delete")
public class DeleteController{

    @Autowired
    private DAOFactory daoFactory;

    public String deleteAd(@RequestParam String id){
        this.daoFactory.getAdDAO().delete(Integer.parseInt(id));
        return "redirect:/ads";
    }

}
