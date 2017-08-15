package ru.yalymar.mvc.controller.filters;

import ru.yalymar.mvc.model.dao.DAOFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
@WebFilter(urlPatterns = "/*")
public class UserFilter implements Filter {

    private final DAOFactory daoFactory = new DAOFactory();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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
        HttpSession session = ((HttpServletRequest) req).getSession();
        String l = (String) session.getAttribute("slogin");
        String p = (String) session.getAttribute("spassword");

            if (sLogin != null && sPassword != null) {
                if (l == null && p == null) {
                    session.setAttribute("slogin", sLogin);
                    session.setAttribute("spassword", sPassword);
                    session.setAttribute("role", this.daoFactory.getUserDAO().
                            getByLoginPassword(sLogin, sPassword).getRole().getRole());
                    chain.doFilter(req, resp);
                    return;
                }
            }
        if (sLogin != null && sPassword != null) {
            if (l != null && p != null) {
                if (!sLogin.equals(l) && !sPassword.equals(p)) {
                    session.setAttribute("slogin", sLogin);
                    session.setAttribute("spassword", sPassword);
                    session.setAttribute("role", this.daoFactory.getUserDAO().
                            getByLoginPassword(sLogin, sPassword).getRole().getRole());
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}