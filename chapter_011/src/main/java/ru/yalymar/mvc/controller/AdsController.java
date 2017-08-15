package ru.yalymar.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.Ad;

import java.util.*;

@Controller
@RequestMapping(value = "/ads")
public class AdsController{

    @Autowired
    private DAOFactory daoFactory;

    public ModelAndView getAllAds(ModelAndView mAV, ModelMap modelMap){
        mAV.setViewName("adsprivate");
        List<Ad> ads = this.daoFactory.getAdDAO().readAll();

        Map<Integer, List<String>> desc = new HashMap<>();
        ads.forEach((ad -> {
            desc.put(ad.getId(), new ArrayList<String>() {{
                add(ad.getCar().getModel().getManuf().getManuf());
                add(ad.getCar().getModel().getModel());
            }});
        }));

        modelMap.addAttribute("ads", ads);
        modelMap.addAttribute("desc", desc);
        modelMap.addAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
        modelMap.addAttribute("models", this.daoFactory.getModelDAO().readAll());
        modelMap.addAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
        modelMap.addAttribute("colours", this.daoFactory.getColorDAO().readAll());
        modelMap.addAttribute("transmissions", this.daoFactory.getTransmissionsDAO().readAll());
        mAV.addAllObjects(modelMap);

        return mAV;
    }

}
