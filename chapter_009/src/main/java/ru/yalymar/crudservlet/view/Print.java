package ru.yalymar.crudservlet.view;

import ru.yalymar.crudservlet.model.UserManager;
import ru.yalymar.crudservlet.model.db.DBManager;
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

    /** return a row from a table as String
     * @param rs
     * @return String
     */
    public void print(ResultSet rs, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        if(rs != null) {
            String str1 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", "id", "name", "login", "email", "dateCreate",
                    System.getProperty("line.separator"));
            String str2 = null;
            try {
                rs.next();
                str2 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", rs.getInt("id"),
                        rs.getString("name"), rs.getString("login"),
                        rs.getString("email"), rs.getTimestamp("dateCreate").toString(),
                        System.getProperty("line.separator"));
            } catch (SQLException e) {
                DBManager.logger.error(e.getMessage(), e);
            }
            String result = str1.concat(str2);
            writer.append(result);
        }
        else {
            writer.append("Something was wrong. Try one more time!");
        }
        writer.flush();
        writer.close();
    }

    /** return all rows from a table as String
     * @return String
     */
    public void printAll(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        ResultSet rs = this.userManager.getAll();
        String str1 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", "id", "name", "login", "email", "dateCreate",
                System.getProperty("line.separator"));

        try {
            while (rs.next()) {
                String str2 = null;
                try {
                    str2 = String.format("%1$-5s%2$-10s%3$-10s%4$-30s%5$-20s%6$s", rs.getInt("id"),
                            rs.getString("name"), rs.getString("login"),
                            rs.getString("email"), rs.getTimestamp("dateCreate").toString(),
                            System.getProperty("line.separator"));
                } catch (SQLException e) {
                    DBManager.logger.error(e.getMessage(), e);
                }
                str1 = str1.concat(str2);
            }
        } catch (SQLException e) {
            DBManager.logger.error(e.getMessage(), e);
        }
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
}
