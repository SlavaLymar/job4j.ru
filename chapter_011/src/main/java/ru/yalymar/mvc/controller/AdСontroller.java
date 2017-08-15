package ru.yalymar.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.Ad;

@Controller
@RequestMapping(value = "/ad")
public class AdСontroller {

    @Autowired
    private DAOFactory daoFactory;

    public ModelAndView getAd(@RequestParam String id,
                              @RequestParam String slogin,
                              @RequestParam String spassword,
                              ModelAndView mAV, ModelMap modelMap){
        mAV.setViewName("ad");
        int i = Integer.parseInt(id);
        Ad ad = this.daoFactory.getAdDAO().read(i);

        modelMap.addAttribute("isAdmin", this.daoFactory.getUserDAO().
                isAdmin(slogin, spassword));
        modelMap.addAttribute("user", this.daoFactory.getUserDAO().
                getByLoginPassword(slogin, spassword));
        modelMap.addAttribute("ad", ad);
        modelMap.addAttribute("model", ad.getCar().getModel().getModel());
        modelMap.addAttribute("body", ad.getCar().getBody().getBody());
        modelMap.addAttribute("color", ad.getCar().getColor().getColor());
        modelMap.addAttribute("m", ad.getCar().getModel().getManuf().getManuf());
        modelMap.addAttribute("transmission", ad.getCar().getTransmission().getName());
        mAV.addAllObjects(modelMap);

        return mAV;
    }

}
