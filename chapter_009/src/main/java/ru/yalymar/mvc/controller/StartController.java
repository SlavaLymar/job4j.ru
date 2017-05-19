package ru.yalymar.mvc.controller;

import ru.yalymar.mvc.model.UserManager;
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
public class StartController extends HttpServlet{

    UserManager userManager = new UserManager();

    /** get user from db
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", this.userManager.getAll());
        req.getRequestDispatcher("/WEB-INF/views/mvcusers.jsp").forward(req, resp);
    }

}
