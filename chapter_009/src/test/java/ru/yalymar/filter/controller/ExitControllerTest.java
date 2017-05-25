package ru.yalymar.filter.controller;

import org.junit.Test;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.*;

public class ExitControllerTest {

    @Test
    public void whenExitShouldGetFalse() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/WEB-INF/views/filter/signin.jsp")).thenReturn(rd);
        when(req.getSession()).thenReturn(session);
        new ExitController().doPost(req, resp);
        verify(session, atLeastOnce()).invalidate();
    }
}