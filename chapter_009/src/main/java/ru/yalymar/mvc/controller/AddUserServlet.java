package ru.yalymar.mvc.controller;

import ru.yalymar.mvc.model.User;
import ru.yalymar.mvc.model.UserManager;

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
public class AddUserServlet extends HttpServlet{

    /**
     * instance of userManager for CRUD operations
     */
    private final UserManager userManager = new UserManager();

    /** update user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), Calendar.getInstance());
        this.userManager.add(user);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
