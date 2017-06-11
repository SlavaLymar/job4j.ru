package ru.yalymar.configuration.controller;

import org.json.simple.JSONObject;
import ru.yalymar.configuration.model.Item;
import ru.yalymar.configuration.model.manager.ManagersFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author slavalymar
 * @since 08.06.2017
 * @version 1
 */
@WebServlet(name = "edit", urlPatterns = "/edit")
public class EditServlet extends HttpServlet{

    private final ManagersFactory mf = new ManagersFactory();

    public ManagersFactory getMf() {
        return this.mf;
    }

    /** get edit form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item item = this.mf.getIm().read(id);
        req.setAttribute("item", item);
        req.getRequestDispatcher("/WEB-INF/configure/views/edit.jsp").forward(req, resp);
    }

    /** edit item
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item item = new Item();
        item.setId(id);
        item.setDescription(req.getParameter("tabledesc"));
        item.setDone(Boolean.parseBoolean(req.getParameter("tabledone")));
        this.mf.getIm().update(id, item);

        //set content-type
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();

        //daoCreate json
        Item newItem = this.mf.getIm().read(id);
        Map<String, String> itemJson = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        itemJson.put("id", String.valueOf(newItem.getId()));
        itemJson.put("description", newItem.getDescription());
        itemJson.put("daoCreate", sdf.format(newItem.getCreated()));
        itemJson.put("done", String.valueOf(newItem.isDone()));
        JSONObject.toJSONString(itemJson);
        JSONObject.writeJSONString(itemJson, writer);
        writer.flush();
    }
}
