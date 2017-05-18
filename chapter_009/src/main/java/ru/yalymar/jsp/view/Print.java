package ru.yalymar.jsp.view;

import ru.yalymar.jsp.model.UserManager;
import ru.yalymar.jsp.model.db.DBManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class Print {

    private final UserManager userManager;

    public Print(UserManager userManager) {
        this.userManager = userManager;
    }

    /** return all rows from a table as String
     * @return String
     */
    public void printAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        //ResultSet rs = this.userManager.getAll();
        StringBuilder sb = new StringBuilder();
        sb.append(
                "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Start</title>" +
                "</head>" +
                "<body>" +
                "<table>" +
                "    <caption ALIGN=center>USERS</caption>" +
                "    <TR>" +
                "        <TD>ID</TD>" +
                "        <TD>NAME</TD>" +
                "        <TD>LOGIN</TD>" +
                "        <TD>EMAIL</TD>" +
                "        <TD>DATE OF CREATE</TD>" +
                "   </TR>");
/*
        try {
            while (rs.next()){
                sb.append("    <TR>" +
                        "        <TD>"+rs.getInt("id")+"</TD>" +
                        "        <TD>"+rs.getString("name")+"</TD>" +
                        "        <TD>"+rs.getString("login")+"</TD>" +
                        "        <TD>"+rs.getString("email")+"</TD>" +
                        "        <TD>"+rs.getTimestamp("datecreate")+"</TD>" +
                        "        <TD>" +
                        "           <form action='" + req.getContextPath() + "/edit' method='get'>" +
                        "           <input type='submit' value='edit'>" +
                        "           <input type='hidden' name='id' value='"+rs.getInt("id")+"'>"+
                        "           </form>" +
                        "        </TD>" +
                        "        <TD>" +
                        "           <form action='" + req.getContextPath() + "/delete' method='post'>" +
                        "           <input type='submit' value='delete'>" +
                        "           <input type='hidden' name='id' value='"+rs.getInt("id")+"'>"+
                        "           </form>" +
                        "        </TD>" +
                        "   </TR>");
            }
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        */
        sb.append(
                "<TD>" +
                        "<form action='" + req.getContextPath() + "/add' method='get'>" +
                        "        <input type='submit' value='+'>" +
                        "    </form>" +
                 "</TD>" +
                "</table>" +
                "</body>" +
                "</html>");

        writer.append(sb);
        writer.flush();
        writer.close();
    }

    public void printError(HttpServletResponse resp, String str) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(str);
        writer.flush();
        writer.close();
    }

    /** print add form
     * @param req
     * @param resp
     * @throws IOException
     */
    public void printAddForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Start</title>" +
                "</head>" +
                "<body>" +
                "    <form action='"+req.getContextPath()+"/add' method='post'>" +
                "        Name: <input type='text' name='name'/>" +
                "        Login: <input type='text' name='login'/>" +
                "        Email: <input type='text' name='email'/>" +
                "        <input type='submit' value='add'>" +
                "    </form>" +
                "</body>" +
                "</html>");
        writer.flush();
        writer.close();
    }

    /** print edit form
     * @param req
     * @param resp
     * @throws IOException
     */
    public void printEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        ResultSet rs = this.userManager.get(req.getParameter("id"));
        String name = null;
        String login = null;
        String email = null;
        String id = null;
        try {
            rs.next();
            name = rs.getString("name");
            login = rs.getString("login");
            email = rs.getString("email");
            id = String.valueOf(rs.getInt("id"));
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Start</title>" +
                "</head>" +
                "<body>" +
                "    <form action='"+req.getContextPath()+"/edit' method='post'>" +
                "        Name: <input type='text' name='name' value='"+name+"'/>" +
                "        Login: <input type='text' name='login' value='"+login+"'/>" +
                "        Email: <input type='text' name='email' value='"+email+"'/>" +
                "        <input type='submit' value='edit'>" +
                "        <input type='hidden' name='id' value='"+id+"'>"+
                "    </form>" +
                "</body>" +
                "</html>");
        try {
            rs.close();
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
        writer.flush();
        writer.close();
    }

}
