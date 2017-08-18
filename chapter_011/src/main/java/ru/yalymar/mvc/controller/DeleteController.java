package ru.yalymar.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yalymar.mvc.model.dao.DAOFactory;

@Controller
public class DeleteController{

    private DAOFactory daoFactory;

    @Autowired
    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAd(@RequestParam String id){
        this.daoFactory.getAdDAO().delete(Integer.parseInt(id));
        return "redirect:ads.do";
    }

}
