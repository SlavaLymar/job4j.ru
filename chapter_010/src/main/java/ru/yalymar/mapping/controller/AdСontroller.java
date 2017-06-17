package ru.yalymar.mapping.controller;

import ru.yalymar.mapping.model.Ad;
import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ad")
public class AdСontroller extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Ad ad = this.daoFactory.getAdDAO().read(id);
        req.setAttribute("ad", ad);
        req.setAttribute("model", ad.getCar().getModel().getModel());
        req.setAttribute("m", ad.getCar().getModel().getManuf().getManuf());
        req.getRequestDispatcher("/WEB-INF/mapping/views/ad.jsp").forward(req, resp);
    }
}
