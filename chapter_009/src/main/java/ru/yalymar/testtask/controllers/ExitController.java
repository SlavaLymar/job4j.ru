package ru.yalymar.testtask.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author slavalymar
 * @version 1
 * @since 04.06.2017
 */
@WebServlet(name = "exit", urlPatterns = "/testexit")
public class ExitController extends HttpServlet {

    /** exit user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("/WEB-INF/views/testtask/testsignin.jsp").forward(req, resp);
    }
}
