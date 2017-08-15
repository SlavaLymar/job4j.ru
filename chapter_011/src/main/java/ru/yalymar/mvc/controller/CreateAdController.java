package ru.yalymar.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.*;

import javax.servlet.annotation.MultipartConfig;
import java.sql.Timestamp;
import java.util.Set;

@Controller
@MultipartConfig(fileSizeThreshold = 1024* 1024* 2,
                    maxFileSize = 1024*1024,
                    maxRequestSize = 1024*1024*2)
public class CreateAdController{

    @Autowired
    private DAOFactory daoFactory;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAddAdForm(ModelAndView mAV){
        mAV.setViewName("add");

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
        modelMap.addAttribute("models", this.daoFactory.getModelDAO().readAll());
        modelMap.addAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
        modelMap.addAttribute("colours", this.daoFactory.getColorDAO().readAll());
        modelMap.addAttribute("transmissions", this.daoFactory.getTransmissionsDAO().readAll());
        mAV.addAllObjects(modelMap);

        return mAV;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createAd(@RequestParam String model,
                           @RequestParam String transmission,
                           @RequestParam String body,
                           @RequestParam String color,
                           @RequestParam String title,
                           @RequestParam String slogin,
                           @RequestParam String spassword){

        Ad ad = new Ad();
        String modelID = req.getParameter("model");
        String transmissionID = req.getParameter("transmission");
        String bodyID = req.getParameter("body");
        String colorID = req.getParameter("color");

        ad.setTittle(req.getParameter("title"));
        ad.setCar(new Car(
                new Model(Integer.parseInt(modelID)),
                new Transmission(Integer.parseInt(transmissionID)),
                new Body(Integer.parseInt(bodyID)),
                new Color(Integer.parseInt(colorID))));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));

        User user = this.daoFactory.getUserDAO().
                getByLoginPassword(
                        (String) req.getSession().getAttribute("slogin"),
                        (String) req.getSession().getAttribute("spassword"));
        ad.setUser(user);
        ad.setDone(false);
        ad.setPrice(Integer.parseInt(req.getParameter("price")));

        Set<Image> images = this.daoFactory.getAdDAO().getFiles(req, resp);
        ad.setImages(images);
        this.daoFactory.getAdDAO().create(ad);

        return "redirect:/ads";
    }


}
