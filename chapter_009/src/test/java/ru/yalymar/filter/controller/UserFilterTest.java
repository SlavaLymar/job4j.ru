package ru.yalymar.filter.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserFilterTest {

    private UserFilter userFilter;
    private AddController us;

    @Before
    public void before() throws SQLException, ServletException, IOException {
        this.userFilter = new UserFilter();
        this.userFilter.init(mock(FilterConfig.class));
        us = new AddController();
        us.getUserManager().getDbManager().createUsersTable();
        this.addAdmin();
    }

    private void addAdmin() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("login")).thenReturn("slava");
        when(req.getParameter("password")).thenReturn("lymar");
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
    public void whenUserSignInShouldMoveToUserArea() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("slogin")).thenReturn("slava");
        when(req.getParameter("spassword")).thenReturn("lymar");
        when(req.getSession()).thenReturn(session);

        this.userFilter.doFilter(req, resp, chain);

        verify(session, atLeastOnce()).setAttribute("slogin", "slava");
    }
}