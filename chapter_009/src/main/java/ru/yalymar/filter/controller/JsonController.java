package ru.yalymar.filter.controller;

import org.json.simple.JSONObject;
import ru.yalymar.filter.model.User;
import ru.yalymar.filter.model.UserManager;
import javax.servlet.ServletException;
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
 * @since 31.06.2017
 * @version 1
 */
public class JsonController extends HttpServlet{

    private UserManager userManager = new UserManager();

    /** get user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        User user = this.userManager.get(req.getParameter("id"));

        //create json
        Map<String, String> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("login", user.getLogin());
        userMap.put("password", user.getPassword());
        userMap.put("email", user.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        userMap.put("date", sdf.format(user.getCreateDate().getTimeInMillis()));
        userMap.put("country", user.getCountry());
        userMap.put("city", user.getCity());
        JSONObject.toJSONString(userMap);
        JSONObject.writeJSONString(userMap, writer);
        writer.flush();
    }


    /** edit user
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.userManager.edit(req);
    }
}
