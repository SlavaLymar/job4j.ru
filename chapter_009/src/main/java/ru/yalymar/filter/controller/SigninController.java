package ru.yalymar.filter.controller;

import ru.yalymar.filter.model.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class SigninController extends HttpServlet{

    private final UserManager userManager = new UserManager();

    /** show sign in form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/filter/signin.jsp").forward(req, resp);
    }

    /** sign in
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(this.userManager.isValid(login, password)){
            req.setAttribute("users", this.userManager.getAll());
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("role", this.userManager.isAdmin(login, password) ? "admin" : "user");
            req.getRequestDispatcher("/WEB-INF/views/filter/mvcusers.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }

    }
}