package ru.yalymar.filter.controller;

import ru.yalymar.filter.model.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 30.05.2017
 * @version 1
 */
public class UController extends HttpServlet {

    private UserManager userManager = new UserManager();

    /** get all users
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
        if(login != null && password != null){
            if(this.userManager.isAdmin(login, password)){
                req.setAttribute("users", this.userManager.getAll());
                req.getRequestDispatcher("/WEB-INF/views/filter/mvcusers.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("user", this.userManager.getByLoginPassword(login, password));
                req.getRequestDispatcher("/WEB-INF/views/filter/mvcusersforuser.jsp").forward(req, resp);
            }
        }
    }
}
