package ru.yalymar.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.Ad;
import ru.yalymar.mvc.model.models.Car;
import ru.yalymar.mvc.model.models.Image;
import ru.yalymar.mvc.model.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

@Controller
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024,
        maxRequestSize = 1024 * 1024 * 2)
public class EditController {

    private final DAOFactory daoFactory = new DAOFactory();

    @RequestMapping(value = "/editad", method = RequestMethod.GET)
    public ModelAndView getEditForm(@RequestParam String id,
                                    ModelAndView mAV,
                                    ModelMap modelMap) {
        mAV.setViewName("edit");
        Ad ad = this.daoFactory.getAdDAO().read(Integer.parseInt(id));

        modelMap.addAttribute("ad", ad);
        modelMap.addAttribute("currentmodel", ad.getCar().getModel().getModel());
        modelMap.addAttribute("currentbody", ad.getCar().getBody().getBody());
        modelMap.addAttribute("currentcolor", ad.getCar().getColor().getColor());
        modelMap.addAttribute("currentmanuf", ad.getCar().getModel().getManuf().getManuf());
        modelMap.addAttribute("currenttransmission", ad.getCar().getTransmission().getName());
        modelMap.addAttribute("currentdone", ad.isDone());
        modelMap.addAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
        modelMap.addAttribute("models", this.daoFactory.getModelDAO().readAll());
        modelMap.addAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
        modelMap.addAttribute("colours", this.daoFactory.getColorDAO().readAll());
        modelMap.addAttribute("transmissions", this.daoFactory.getTransmissionsDAO().readAll());
        mAV.addAllObjects(modelMap);

        return mAV;
    }

    @RequestMapping(value = "/editad", method = RequestMethod.POST)
    public String editAd(HttpServletRequest req,
                         @RequestParam String id,
                         @RequestParam String model,
                         @RequestParam String transmission,
                         @RequestParam String body,
                         @RequestParam String color,
                         @RequestParam String title,
                         @RequestParam String done,
                         @RequestParam String price,
                         @RequestParam String slogin,
                         @RequestParam String spassword) {

        Ad newAd = new Ad();
        newAd.setTittle(title);
        newAd.setCar(new Car(
                this.daoFactory.getModelDAO().read(Integer.parseInt(model)),
                this.daoFactory.getTransmissionsDAO().read(Integer.parseInt(transmission)),
                this.daoFactory.getBodyDAO().read(Integer.parseInt(body)),
                this.daoFactory.getColorDAO().read(Integer.parseInt(color))));
        newAd.setCreate(new Timestamp(System.currentTimeMillis()));

        User user = this.daoFactory.getUserDAO().getByLoginPassword(slogin, spassword);
        newAd.setUser(user);
        newAd.setDone(Boolean.parseBoolean(done));
        newAd.setPrice(Integer.parseInt(price));

        Set<Image> images = null;
        try {
            images = this.daoFactory.getAdDAO().getFiles(req);
        } catch (IOException | ServletException e) {
            DAOFactory.logger.error(e.getMessage(), e);
        }
        newAd.setImages(images);

        this.daoFactory.getAdDAO().update(Integer.parseInt(id), newAd);

        return "redirect:/ads";
    }

}
