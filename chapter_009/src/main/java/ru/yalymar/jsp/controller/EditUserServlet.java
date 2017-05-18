package ru.yalymar.jsp.controller;

import ru.yalymar.jsp.model.UserManager;
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
public class EditUserServlet extends HttpServlet{

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
        this.userManager.edit(req);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

}
