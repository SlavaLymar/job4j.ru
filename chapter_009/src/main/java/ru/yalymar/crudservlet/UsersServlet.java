package ru.yalymar.crudservlet;

import org.apache.log4j.Logger;
import ru.yalymar.crudservlet.db.DBManager;
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

public class UsersServlet extends HttpServlet{

    private static final Logger LOGGER = Logger.getLogger(UsersServlet.class);
    private final DBManager dbManager = new DBManager();
    private final UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        resp.setContentType("text/html");
        writer.append(req.getParameter("id"));
        writer.flush();
        ResultSet rs = this.userManager.get(this.dbManager, req.getParameter("id"));
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), Calendar.getInstance());
        int i = this.userManager.edit(this.dbManager, req.getParameter("id"), newUser);
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if(i > 0) {
            this.doGet(req, resp);
        }
        else {
            writer.append("Id`s not found!");
        }
        writer.flush();
        writer.close();

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), Calendar.getInstance());
        int i = this.userManager.add(this.dbManager, user);
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if(i > 0) {
            this.userManager.getAll(this.dbManager);
        }
        else {
            writer.append(String.format("%s, you didnt add. Try one more time!!!",
                    req.getParameter("name")));
        }
        writer.flush();
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int i = this.userManager.delete(this.dbManager, req.getParameter("id"));
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if(i > 0) {
            this.userManager.getAll(this.dbManager);
        }
        else {
            writer.append("Id`s not found!");
        }
        writer.flush();
        writer.close();
    }
}
