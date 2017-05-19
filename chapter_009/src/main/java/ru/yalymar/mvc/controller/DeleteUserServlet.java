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
public class DeleteUserServlet extends HttpServlet{
    /**
     * instance of userManager for CRUD operations
     */
    private final UserManager userManager = new UserManager();

    /** delete user from db
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.userManager.delete(req.getParameter("id"));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
