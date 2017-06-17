package ru.yalymar.mapping.controller;

import ru.yalymar.mapping.model.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete")
public class DeleteController extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        this.daoFactory.getAdDAO().delete(id);
//        resp.sendRedirect(String.format("%s/ads", req.getContextPath()));
    }
}
