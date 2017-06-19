package ru.yalymar.mapping.controller;

import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

@WebServlet(urlPatterns = "/editad")
@MultipartConfig(fileSizeThreshold = 1024* 1024* 2,
        maxFileSize = 1024*1024,
        maxRequestSize = 1024*1024*2)
public class EditController extends HttpServlet{

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Ad ad = this.daoFactory.getAdDAO().read(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("ad", ad);
        req.setAttribute("currentmodel", ad.getCar().getModel().getModel());
        req.setAttribute("currentbody", ad.getCar().getBody().getBody());
        req.setAttribute("currentcolor", ad.getCar().getColor().getColor());
        req.setAttribute("currentmanuf", ad.getCar().getModel().getManuf().getManuf());
        req.setAttribute("currenttransmission", ad.getCar().getTransmission().getName());
        req.setAttribute("currentdone", ad.isDone());
        req.setAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
        req.setAttribute("models", this.daoFactory.getModelDAO().readAll());
        req.setAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
        req.setAttribute("colours", this.daoFactory.getColorDAO().readAll());
        req.setAttribute("transmissions", this.daoFactory.getTransmissionsDAO().readAll());
        req.getRequestDispatcher("/WEB-INF/mapping/views/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int adID = Integer.parseInt(req.getParameter("id"));
        String modelID = req.getParameter("model");
        String transmissionID = req.getParameter("transmission");
        String bodyID = req.getParameter("body");
        String colorID = req.getParameter("color");

        Ad newAd = new Ad();
        newAd.setTittle(req.getParameter("title"));
        newAd.setCar(new Car(
                this.daoFactory.getModelDAO().read(Integer.parseInt(modelID)),
                this.daoFactory.getTransmissionsDAO().read(Integer.parseInt(transmissionID)),
                this.daoFactory.getBodyDAO().read(Integer.parseInt(bodyID)),
                this.daoFactory.getColorDAO().read(Integer.parseInt(colorID))));
        newAd.setCreate(new Timestamp(System.currentTimeMillis()));

        User user = this.daoFactory.getUserDAO().
                getByLoginPassword(
                        (String) req.getSession().getAttribute("slogin"),
                        (String) req.getSession().getAttribute("spassword"));
        newAd.setUser(user);
        newAd.setDone(Boolean.parseBoolean(req.getParameter("done")));
        newAd.setPrice(Integer.parseInt(req.getParameter("price")));

        Set<Image> images = this.daoFactory.getAdDAO().getFiles(req, resp);
        newAd.setImages(images);

        this.daoFactory.getAdDAO().update(adID, newAd);
        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }
}
