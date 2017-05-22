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
public class EditController extends HttpServlet{

    /**
     * instance of userManager for CRUD operations
     */
    private final UserManager userManager = new UserManager();

    /** get edit form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", this.userManager.get(req.getParameter("id")));
        req.getRequestDispatcher("/WEB-INF/views/mvcedit.jsp").forward(req, resp);
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
        this.userManager.edit(req);
        req.setAttribute("users", this.userManager.getAll());
        req.getRequestDispatcher("/WEB-INF/views/mvcusers.jsp").forward(req, resp);
    }

}
