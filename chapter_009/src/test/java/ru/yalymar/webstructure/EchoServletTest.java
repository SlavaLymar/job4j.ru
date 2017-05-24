package ru.yalymar.webstructure;

import org.junit.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EchoServletTest {

    @Test
    public void whenSendReqShouldGetResp() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        EchoServlet echo = new EchoServlet();

        when(resp.getWriter()).thenReturn(writer);
        echo.doGet(req, resp);
        String s = stringWriter.getBuffer().toString();

        assertTrue("hello world!".equals(s));
    }
}