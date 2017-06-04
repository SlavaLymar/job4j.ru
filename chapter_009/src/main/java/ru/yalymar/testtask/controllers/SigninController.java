package ru.yalymar.testtask.controllers;

import ru.yalymar.testtask.model.dao.DAOFabric;
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
@WebServlet(name = "sign in", urlPatterns = "/testtask")
public class SigninController extends HttpServlet{

    private final DAOFabric daoFabric = new DAOFabric();

    /** show sign in form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/testtask/testsignin.jsp").forward(req, resp);
    }

    /** sign in
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("slogin");
        String password = req.getParameter("spassword");
        if(this.daoFabric.getUserManager().isValid(login, password)){
            resp.sendRedirect(String.format("%s/users1", req.getContextPath()));
        }
        else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }

    }
}