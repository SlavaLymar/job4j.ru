package ru.yalymar.jsp.controller;

import org.apache.log4j.Logger;
import ru.yalymar.jsp.model.UserManager;
import ru.yalymar.jsp.view.Print;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class UsersServlet extends HttpServlet{

    /** get user from db
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

}
