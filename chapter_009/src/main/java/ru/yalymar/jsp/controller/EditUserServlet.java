package ru.yalymar.jsp.controller;

import ru.yalymar.jsp.model.UserManager;
import ru.yalymar.jsp.view.Print;

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

    private final Print print = new Print(userManager);

    public UserManager getUserManager() {
        return this.userManager;
    }

    /** get user from db
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.print.printEditForm(req, resp);
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
        this.print.printAllUsers(req, resp);
    }

}
