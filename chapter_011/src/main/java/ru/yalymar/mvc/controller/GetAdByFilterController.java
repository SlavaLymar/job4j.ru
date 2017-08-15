package ru.yalymar.mvc.controller;

import org.json.simple.JSONObject;
import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.Ad;

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

@WebServlet(urlPatterns = "/adfilter")
public class GetAdByFilterController extends HttpServlet {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();

        String manuf = req.getParameter("fmanuf");
        String model = req.getParameter("fmodel");
        String transmission = req.getParameter("ftransmission");
        String body = req.getParameter("fbody");
        String color = req.getParameter("fcolor");
        String from = req.getParameter("from");
        String to = req.getParameter("to");

        Map<String, String> filterData = new HashMap<>();
        filterData.put("manuf", manuf);
        filterData.put("model", model);
        filterData.put("transmission", transmission);
        filterData.put("body", body);
        filterData.put("color", color);
        filterData.put("from", from);
        filterData.put("to", to);
        List<Ad> ads = this.daoFactory.getAdDAO().getAdByFilters(filterData);

        //create json
        Map<String, List<Integer>> jAdsId = new HashMap<>();
        List<Integer> adsID = new ArrayList<>();
        ads.forEach(ad -> {
            adsID.add(ad.getId());
        });
        jAdsId.put("id", adsID);

        JSONObject.toJSONString(jAdsId);
        JSONObject.writeJSONString(jAdsId, writer);
        writer.flush();
    }
}
