package ru.yalymar.filter.controller;

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
import static org.mockito.Mockito.*;

public class UserFilterTest {

    private UserFilter userFilter;

    @Before
    public void initSet() throws ServletException {
        this.userFilter = new UserFilter();
        this.userFilter.init(mock(FilterConfig.class));
    }

    @Test
    public void whenUserSignInShouldMoveToUserArea() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getParameter("login")).thenReturn("slava");
        when(req.getParameter("password")).thenReturn("lymar");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/views/filter/mvcusersforuser.jsp")).thenReturn(rd);

        this.initSet();
        this.userFilter.doFilter(req, resp, chain);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/views/filter/mvcusersforuser.jsp");
    }
}