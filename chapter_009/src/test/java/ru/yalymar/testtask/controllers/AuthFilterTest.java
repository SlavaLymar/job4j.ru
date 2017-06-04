package ru.yalymar.testtask.controllers;

import org.junit.Test;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthFilterTest {

    @Test
    public void whenSigninServletShouldDoIt() throws IOException, ServletException {
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest httpReq = mock(HttpServletRequest.class);
        HttpServletResponse httpResp = mock(HttpServletResponse.class);

        when(httpReq.getRequestURI()).thenReturn("/");
        when(httpReq.getSession()).thenReturn(session);

        new AuthFilter().doFilter(httpReq, httpResp, chain);
        verify(httpResp).sendRedirect(String.format("%s/testtask", httpReq.getContextPath()));
    }
}