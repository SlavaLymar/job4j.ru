package ru.yalymar.crudservlet;

import org.apache.log4j.Logger;
import ru.yalymar.crudservlet.model.User;
import ru.yalymar.crudservlet.model.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        ResultSet rs = this.userManager.get(req.getParameter("id"));
        if(rs != null) {
            writer.append(this.userManager.print(rs));
        }
        else {
            writer.append("Something was wrong. Try one more time!");
        }
        try {
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        writer.flush();
        writer.close();
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
        else {resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.append("Id`s not found!");
            writer.flush();
            writer.close();
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
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if(i > 0) {
            writer.append(this.userManager.printAll());
        }
        else {
            writer.append(String.format("%s, you didnt add. Try one more time!!!",
                    req.getParameter("name")));
        }
        writer.flush();
        writer.close();
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
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if(i > 0) {
            writer.append(this.userManager.printAll());
        }
        else {
            writer.append("Id`s not found!");
        }
        writer.flush();
        writer.close();
    }
}
