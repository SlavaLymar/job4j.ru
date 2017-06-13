package ru.yalymar.mapping.controller.auth;

import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class SigninController extends HttpServlet{

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/mapping/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("slogin");
        String password = req.getParameter("spassword");
        if(this.daoFactory.getUserDAO().isValid(login, password)){
            resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
        }
        else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }

    }
}