package ru.yalymar.filter.controller;

import ru.yalymar.filter.model.UserManager;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 15.05.2017
 * @version 1
 */
public class UserFilter implements Filter {

    private UserManager userManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.userManager = new UserManager();
    }

    /** determine user or admin
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();
        synchronized (session) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String newlogin = request.getParameter("newlogin");
            String newpassword = request.getParameter("newpassword");
            String id = request.getParameter("id");

            if (login != null && password != null && !this.userManager.isAdmin(login, password)) {
                if(newlogin != null || newpassword != null) {
                    chain.doFilter(req, resp);
                }
                req.setAttribute("user", this.userManager.getByLoginPassword(login, password));
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                session.setAttribute("id", id);
                req.getRequestDispatcher("/WEB-INF/views/filter/mvcusersforuser.jsp").forward(req, resp);
                return;
            }
        }
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        this.userManager = null;
    }
}