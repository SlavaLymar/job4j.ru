package ru.yalymar.filter.controller;

import ru.yalymar.filter.model.UserManager;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String sLogin = request.getParameter("slogin");
        String sPassword = request.getParameter("spassword");
        String l = (String) ((HttpServletRequest) req).getSession().getAttribute("slogin");
        String p = (String) ((HttpServletRequest) req).getSession().getAttribute("spassword");

            if (sLogin != null && sPassword != null) {
                if (l == null && p == null) {
                    ((HttpServletRequest) req).getSession().setAttribute("slogin", sLogin);
                    ((HttpServletRequest) req).getSession().setAttribute("spassword", sPassword);
                    ((HttpServletRequest) req).getSession().setAttribute(
                            "role", this.userManager.isAdmin(sLogin, sPassword) ? "admin" : "user");
                    chain.doFilter(req, resp);
                    return;
                }
            }
        if (sLogin != null && sPassword != null) {
            if (l != null && p != null) {
                if (!sLogin.equals(l) && !sPassword.equals(p)) {
                    ((HttpServletRequest) req).getSession().setAttribute("slogin", sLogin);
                    ((HttpServletRequest) req).getSession().setAttribute("spassword", sPassword);
                    ((HttpServletRequest) req).getSession().setAttribute(
                            "role", this.userManager.isAdmin(sLogin, sPassword) ? "admin" : "user");
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        this.userManager = null;
    }
}