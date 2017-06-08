package ru.yalymar.configuration.controller;

import org.junit.Test;
import ru.yalymar.configuration.model.Item;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteServletTest {

    @Test
    public void whenAddItemShouldGetIt()
            throws ServletException, IOException, SQLException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ItemsServlet is = new ItemsServlet();

        when(req.getParameter("description")).thenReturn("test2");
        when(resp.getWriter()).thenReturn(new PrintWriter(System.out));

        // add item
        is.doPost(req, resp);
        List<Item> items = is.getMf().getIm().readAll();
        boolean[] findItem = new boolean[1];
        items.forEach((item)->{
            if("test2".equals(item.getDescription())){
                findItem[0] = true;
                return;
            }
        });
        assertTrue(findItem[0]);
    }
}