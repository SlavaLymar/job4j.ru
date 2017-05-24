package ru.yalymar.webstructure;

import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author slavalymar
 * @since 14.05.2017
 * @version 1
 */
public class EchoServlet extends HttpServlet{

    private static final Logger logger = Logger.getLogger(EchoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append("hello world!");
        writer.flush();
    }
}
