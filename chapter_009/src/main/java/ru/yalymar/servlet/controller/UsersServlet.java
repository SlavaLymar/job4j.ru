package ru.yalymar.servlet.controller;

import org.apache.log4j.Logger;
import ru.yalymar.servlet.model.UserManager;
import ru.yalymar.servlet.view.Print;
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
public class UsersServlet extends HttpServlet{

    private static final Logger LOGGER = Logger.getLogger(UsersServlet.class);

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
        this.print.printAllUsers(resp);
    }

}
