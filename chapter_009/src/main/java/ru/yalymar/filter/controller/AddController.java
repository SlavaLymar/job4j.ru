package ru.yalymar.filter.controller;

import ru.yalymar.filter.model.User;
import ru.yalymar.filter.model.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class AddController extends HttpServlet{

    /**
     * instance of userManager for CRUD operations
     */
    private final UserManager userManager = new UserManager();

    /** get add form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/filter/mvcadd.jsp").forward(req, resp);
    }

    /** update user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(req.getParameter("login"), req.getParameter("password"),
                req.getParameter("email"), Calendar.getInstance());
        this.userManager.add(user);
        req.setAttribute("users", this.userManager.getAll());
        req.getRequestDispatcher("/WEB-INF/views/filter/mvcusers.jsp").forward(req, resp);
    }
}
