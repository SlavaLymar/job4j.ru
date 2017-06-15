package ru.yalymar.mapping.controller;

import ru.yalymar.mapping.model.Ad;
import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ads")
public class AdsController extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("slogin");
        String password = (String) req.getSession().getAttribute("spassword");
        if(this.daoFactory.getUserDAO().isValid(login, password)){
            List<Ad> ads = this.daoFactory.getAdDAO().readAll();
            req.setAttribute("ads", ads);
            req.setAttribute("userId",
                    this.daoFactory.getUserDAO().getByLoginPassword(login, password).getId());
            if(this.daoFactory.getUserDAO().isAdmin(login, password)) {
                req.getRequestDispatcher("/WEB-INF/mapping/views/adsprivate.jsp").forward(req, resp);
            }
            else {
                req.getRequestDispatcher("/WEB-INF/mapping/views/ads.jsp").forward(req, resp);
            }
        }
        else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }
    }
}
