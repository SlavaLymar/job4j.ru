package ru.yalymar.mapping.controller;

import jdk.internal.util.xml.impl.Input;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/add")
@MultipartConfig
public class CreateAdController extends HttpServlet{

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
        req.setAttribute("models", this.daoFactory.getModelDAO().readAll());
        req.setAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
        req.setAttribute("colours", this.daoFactory.getColorDAO().readAll());
        req.setAttribute("transmissions", this.daoFactory.getTransmissionsDAO().readAll());
        req.getRequestDispatcher("/WEB-INF/mapping/views/add.jsp").forward(req,  resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
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

        Set<Image> images = this.daoFactory.getAdDAO().getFiles(req, resp, getServletContext());
        ad.setImages(images);
//        this.daoFactory.getAdDAO().create(ad);
        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }


}
