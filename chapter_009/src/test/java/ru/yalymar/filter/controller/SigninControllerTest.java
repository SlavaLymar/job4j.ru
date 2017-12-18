package ru.yalymar.filter.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class SigninControllerTest {

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
    public void whenSignInShouldGetRequesDispatcher() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getParameter("slogin")).thenReturn("admin");
        when(req.getParameter("spassword")).thenReturn("admin");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/views/filter/mvcusers.jsp")).thenReturn(rd);
        when(req.getRequestDispatcher("/WEB-INF/views/filter/signin.jsp")).thenReturn(rd);

        new SigninController().doPost(req, resp);
        verify(resp, atLeastOnce()).sendRedirect(anyString());
    }
}