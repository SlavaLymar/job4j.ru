package ru.yalymar.servlet.controller;

import org.apache.log4j.Logger;
import ru.yalymar.servlet.model.User;
import ru.yalymar.servlet.model.UserManager;
import ru.yalymar.servlet.view.Print;
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

    private static final Logger LOGGER = Logger.getLogger(AddUserServlet.class);

    /**
     * instance of userManager for CRUD operations
     */
    private final UserManager userManager = new UserManager();

    private final Print print = new Print(userManager);

    public UserManager getUserManager() {
        return this.userManager;
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
        User user = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), Calendar.getInstance());
        int i = this.userManager.add(user);
        if(i > 0) {
            this.print.printAllUsers(req, resp);
        }
        else {
            this.print.printError(resp, String.format("%s, you didnt add. Try one more time!!!",
                    req.getParameter("name")));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.print.printAddForm(req, resp);
    }
}
