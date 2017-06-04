package ru.yalymar.testtask.controllers;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SigninControllerTest {

    @Test
    public void whenSignInShouldGetRequesDispatcher() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getParameter("slogin")).thenReturn("admin");
        when(req.getParameter("spassword")).thenReturn("admin");
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/views/testtask/usersforadmin.jsp")).thenReturn(rd);

        new SigninController().doPost(req, resp);
        verify(resp, atLeastOnce()).sendRedirect(String.format("%s/users1", req.getContextPath()));
    }
}