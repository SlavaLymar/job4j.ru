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
                new Model(Integer.parseInt(req.getParameter("modelId"))),
                new Transmission(Integer.parseInt(req.getParameter("transmissoinId"))),
                new Body(Integer.parseInt(req.getParameter("bodyId"))),
                new Color(Integer.parseInt(req.getParameter("colorId")))));
        ad.setCreate(new Timestamp(System.currentTimeMillis()));
        ad.setUser(new User(Integer.parseInt(req.getParameter("userId"))));
        ad.setDone(false);
        ad.setPrice(Integer.parseInt(req.getParameter("price")));

        //TODO imgs

        this.daoFactory.getAdDAO().create(ad);
    }
}
