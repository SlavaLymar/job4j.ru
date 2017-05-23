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
public class UserEditController extends HttpServlet {

    private final UserManager userManager = new UserManager();

    /** get edit form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("user", this.userManager.get(id));
        req.getRequestDispatcher("/WEB-INF/views/filter/edituser.jsp").forward(req, resp);
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
        req.setAttribute("user", this.userManager.get(req.getParameter("id")));
        req.getRequestDispatcher("/WEB-INF/views/filter/mvcusersforuser.jsp").forward(req, resp);
    }
}
