package ru.yalymar.filter.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yalymar.filter.controller.AddController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserManagerTest {

    private AddController us;

    @Before
    public void before() throws SQLException, ServletException, IOException {
        us = new AddController();
        us.getUserManager().getDbManager().createUsersTable();
        this.addAdmin();
    }

    private void addAdmin() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("login")).thenReturn("admin");
        when(req.getParameter("password")).thenReturn("admin");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");
        when(req.getParameter("country")).thenReturn("USA");
        when(req.getParameter("city")).thenReturn("Los Angeles");

        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(("/WEB-INF/views/filter/mvcusers.jsp"))).thenReturn(rd);

        this.us.doPost(req, resp);
    }

    @After
    public void after() throws SQLException {
        us.getUserManager().getDbManager().dropUsersTable();
    }

    @Test
    public void whenSignInShouldGetTrue() {
        UserManager userManager = new UserManager();
        boolean result = userManager.isValid("admin", "admin");
        assertTrue(result);
    }

}