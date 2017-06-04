package ru.yalymar.testtask.controllers;

import org.junit.Test;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.*;

public class UControllerTest {

    @Test
    public void whenSignInShouldGetRequesDispatcher() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("slogin")).thenReturn("admin");
        when(session.getAttribute("spassword")).thenReturn("admin");
        when(req.getRequestDispatcher("/WEB-INF/views/testtask/usersforadmin.jsp")).thenReturn(rd);

        new UController().doGet(req, resp);
        verify(req, atLeastOnce()).getRequestDispatcher("/WEB-INF/views/testtask/usersforadmin.jsp");
    }
}