package ru.yalymar.mapping.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/getdesc")
public class GetDescController extends HttpServlet {

    /*
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
    */
}
