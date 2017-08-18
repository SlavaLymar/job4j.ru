package ru.yalymar.mvc.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yalymar.mvc.model.dao.DAOFactory;
import ru.yalymar.mvc.model.models.Ad;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetAdByFilterController {

    private DAOFactory daoFactory;

    @Autowired
    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @RequestMapping(value = "/adfilter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getAdsByFilter(HttpServletResponse resp,
                               @RequestParam String fmanuf,
                               @RequestParam String fmodel,
                               @RequestParam String ftransmission,
                               @RequestParam String fbody,
                               @RequestParam String fcolor,
                               @RequestParam String from,
                               @RequestParam String to
                                ) {
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            Map<String, String> filterData = new HashMap<>();
            filterData.put("manuf", fmanuf);
            filterData.put("model", fmodel);
            filterData.put("transmission", ftransmission);
            filterData.put("body", fbody);
            filterData.put("color", fcolor);
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
        } catch (IOException e) {
            DAOFactory.logger.error(e.getMessage(), e);
        }
    }
}
