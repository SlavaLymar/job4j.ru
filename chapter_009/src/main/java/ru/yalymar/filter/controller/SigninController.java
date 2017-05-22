package ru.yalymar.filter.controller;

import ru.yalymar.filter.model.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SigninController extends HttpServlet{

    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/filter/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(this.userManager.isValid(login, password)){
            req.setAttribute("users", this.userManager.getAll());
            req.getSession().setAttribute("login", login);
            req.getRequestDispatcher("/WEB-INF/views/filter/mvcusers.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }

    }
}
