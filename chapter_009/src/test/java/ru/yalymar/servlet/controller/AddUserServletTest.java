package ru.yalymar.servlet.controller;

import org.junit.Test;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddUserServletTest {

    @Test
    public void whenAddUserShouldGetResultSet() throws ServletException, IOException, SQLException {
        /*HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter writer = new PrintWriter(System.out);
        UserServlet us = new UserServlet();

        when(req.getParameter("name")).thenReturn("slava");
        when(req.getParameter("login")).thenReturn("slava123");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");
        when(resp.getWriter()).thenReturn(writer);

        us.doPut(req, resp);
        ResultSet rs = us.getUserManager().getAll();
        int id = 0;
        boolean result = false;
        while(rs.next()){
            if("slava".equals(rs.getString("name"))){
                if("slava123".equals(rs.getString("login"))){
                    if("slava123@gmail.nz".equals(rs.getString("email"))){
                        result = true;
                        id = rs.getInt("id");
                        break;
                    }
                }
            }
        }
        assertTrue(result);
        rs.close();

        when(req.getParameter("id")).thenReturn(String.valueOf(id));
        us.doDelete(req, resp);
        ResultSet rs1 = us.getUserManager().getAll();
        boolean result1 = false;
        while(rs1.next()){
            if("slava".equals(rs1.getString("name"))){
                if("slava123".equals(rs1.getString("login"))){
                    if("slava123@gmail.nz".equals(rs1.getString("email"))){
                        result1 = true;
                        break;
                    }
                }
            }
        }
        assertFalse(result1);
        writer.close();
    }
    */
    }
}