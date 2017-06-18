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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/getdesc")
public class GetDescController extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        Ad ad = this.daoFactory.getAdDAO().read(id);

        //create json
        Map<String, String> desc = new HashMap<String, String>(){{
            put("manufactor", ad.getCar().getModel().getManuf().getManuf());
            put("model", ad.getCar().getModel().getModel());
        }};

        JSONObject.toJSONString(desc);
        JSONObject.writeJSONString(desc, writer);
        writer.flush();
    }
}
