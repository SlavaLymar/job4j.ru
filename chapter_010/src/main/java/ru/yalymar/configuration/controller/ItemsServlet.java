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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author slavalymar
 * @since 08.06.2017
 * @version 1
 */
@WebServlet(name = "items", urlPatterns = "/items")
public class ItemsServlet extends HttpServlet{

    private final ManagersFactory mf = new ManagersFactory();

    public ManagersFactory getMf() {
        return this.mf;
    }

    /** get items
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> items = this.mf.getIm().readAll();
        req.setAttribute("items", items);
        req.getRequestDispatcher("/WEB-INF/configure/views/items.jsp").forward(req, resp);
    }

    /** add item
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //add item
        String description = req.getParameter("description");
        Item item = new Item();
        item.setDescription(description);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        int id = this.mf.getIm().create(item);

        //set content-type
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();

        //create json
        Map<String, String> itemJson = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        itemJson.put("id", String.valueOf(id));
        itemJson.put("description", item.getDescription());
        itemJson.put("create", sdf.format(item.getCreated()));
        itemJson.put("done", String.valueOf(item.isDone()));
        JSONObject.toJSONString(itemJson);
        JSONObject.writeJSONString(itemJson, writer);
        writer.flush();
    }
}
