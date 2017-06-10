package ru.yalymar.mapping.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("slogin");
        String password = (String) req.getSession().getAttribute("spassword");
        if(login != null && password != null){
//            if(this.daoFabric.getUserManager().isAdmin(login, password)){
//                req.setAttribute("users", this.daoFabric.getUserManager().getAll());
                req.getRequestDispatcher("/WEB-INF/views/testtask/usersforadmin.jsp").forward(req, resp);
            }
            else {
//                req.setAttribute("user", this.daoFabric.getUserManager().getByLoginPassword(login, password));
                req.getRequestDispatcher("/WEB-INF/views/testtask/userforuser.jsp").forward(req, resp);
            }
//        }
    }
}
