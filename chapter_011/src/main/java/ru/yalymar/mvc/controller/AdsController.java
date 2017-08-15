package ru.yalymar.mvc.controller;

import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
@WebServlet(urlPatterns = "/ads")
public class AdsController extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    /** get ads
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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
            req.setAttribute("manufacturers", this.daoFactory.getManufactorDAO().readAll());
            req.setAttribute("models", this.daoFactory.getModelDAO().readAll());
            req.setAttribute("bodies", this.daoFactory.getBodyDAO().readAll());
            req.setAttribute("colours", this.daoFactory.getColorDAO().readAll());
            req.setAttribute("transmissions", this.daoFactory.getTransmissionsDAO().readAll());

            req.getRequestDispatcher("/WEB-INF/mapping/views/adsprivate.jsp").forward(req, resp);

        } else {
            req.setAttribute("error", "User has not exist!");
            this.doGet(req, resp);
        }
    }
}
