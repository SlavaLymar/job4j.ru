package ru.yalymar.crudservlet.controller;

import org.apache.log4j.Logger;
import ru.yalymar.crudservlet.model.User;
import ru.yalymar.crudservlet.model.UserManager;
import ru.yalymar.crudservlet.view.Print;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

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
        ResultSet rs = this.userManager.get(req.getParameter("id"));
        this.print.print(rs, resp);
        try {
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
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
        User newUser = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), Calendar.getInstance());
        int i = this.userManager.edit(req.getParameter("id"), newUser);

        if(i > 0) {
            this.doGet(req, resp);
        }
        else{
            this.print.printError(resp, "Id`s not found!");
        }
    }

    /** add user to db
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), Calendar.getInstance());
        int i = this.userManager.add(user);
        if(i > 0) {
            this.print.printAll(resp);
        }
        else {
            this.print.printError(resp, String.format("%s, you didnt add. Try one more time!!!",
                    req.getParameter("name")));
        }
    }

    /** delete user from db
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int i = this.userManager.delete(req.getParameter("id"));
        if(i > 0) {
            this.print.printAll(resp);
        }
        else {
            this.print.printError(resp, "Id`s not found!");
        }
    }
}
