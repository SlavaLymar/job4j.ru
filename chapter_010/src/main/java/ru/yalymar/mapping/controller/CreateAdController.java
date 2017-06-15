package ru.yalymar.mapping.controller;

import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/add")
public class CreateAdController extends HttpServlet{

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
        req.setAttribute("models", this.daoFactory.getModelDAO().readAll());
        req.setAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
        req.setAttribute("colours", this.daoFactory.getColorDAO().readAll());
        req.getRequestDispatcher("/WEB-INF/mapping/views/add.jsp").forward(req,  resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Ad ad = new Ad();

        ad.setTittle(req.getParameter("title"));
        ad.setCar(new Car(
                new Model(Integer.parseInt(req.getParameter("model"))),
                new Transmission(Integer.parseInt(req.getParameter("transmissoin"))),
                new Body(Integer.parseInt(req.getParameter("body"))),
                new Color(Integer.parseInt(req.getParameter("color")))));
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
    }
}
