package ru.yalymar.mapping.controller;

import org.json.simple.JSONObject;
import ru.yalymar.mapping.model.Ad;
import ru.yalymar.mapping.model.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/ads")
public class AdsController extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("slogin");
        String password = (String) req.getSession().getAttribute("spassword");
        if (this.daoFactory.getUserDAO().isValid(login, password)) {
            List<Ad> ads = this.daoFactory.getAdDAO().readAll();
            req.setAttribute("ads", ads);

            Map<Integer, List<String>> desc = new HashMap<>();
            ads.forEach((ad -> {
                desc.put(ad.getId(), new ArrayList<String>() {{
                    add(ad.getCar().getModel().getManuf().getManuf());
                    add(ad.getCar().getModel().getModel());
                }});
            }));
            req.setAttribute("desc", desc);

            req.getRequestDispatcher("/WEB-INF/mapping/views/adsprivate.jsp").forward(req, resp);

        } else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }
    }
}
