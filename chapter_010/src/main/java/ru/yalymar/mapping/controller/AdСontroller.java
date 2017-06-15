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
        Ad ad = this.daoFactory.getAdDAO().read(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("ad", ad);
        req.getRequestDispatcher("/WEB-INF/mapping/views/ad.jsp").forward(req, resp);
    }
}
