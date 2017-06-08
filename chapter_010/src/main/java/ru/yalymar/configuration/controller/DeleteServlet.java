package ru.yalymar.configuration.controller;

import ru.yalymar.configuration.model.manager.ManagersFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author slavalymar
 * @since 08.06.2017
 * @version 1
 */
@WebServlet(name = "del", urlPatterns = "/del")
public class DeleteServlet extends HttpServlet {

    private final ManagersFactory mf = new ManagersFactory();

    public ManagersFactory getMf() {
        return this.mf;
    }

    /** delete item
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.mf.getIm().delete(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("users", this.mf.getIm().readAll());
        req.getRequestDispatcher("/WEB-INF/views/configure/item.jsp").forward(req, resp);
    }
}
