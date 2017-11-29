package ru.yalymar.crudservlet.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServletTest {

    private UserServlet us;

    @Before
    public void before() throws SQLException {
        us = new UserServlet();
        us.getUserManager().getDbManager().createUsersTable();
    }

    @After
    public void afterClass() throws SQLException {
        us.getUserManager().getDbManager().dropUsersTable();
    }

    @Test
    public void whenAddUserShouldGetResultSet() throws ServletException, IOException, SQLException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(req.getParameter("name")).thenReturn("slava");
        when(req.getParameter("login")).thenReturn("slava123");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");
        when(resp.getWriter()).thenReturn(writer);

        // add user
        us.doPut(req, resp);
        String s = stringWriter.getBuffer().toString();

        assertTrue(s.contains("slava123@gmail.nz"));
        writer.close();
    }

    @Test
    public void whenDeleteUserShouldGetResultSet() throws ServletException, IOException, SQLException, InterruptedException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(req.getParameter("name")).thenReturn("slava");
        when(req.getParameter("login")).thenReturn("slava123");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");
        when(resp.getWriter()).thenReturn(writer);

        us.doPut(req, resp);

        // find user
        ResultSet rs = us.getUserManager().getAll();
        String id = null;
        while(rs.next()) {
            if("slava".equals(rs.getString("name").trim())){
                id = rs.getString("id");
                break;
            }
        }

        rs.close();

        //delete user
        when(req.getParameter("id")).thenReturn(id);
        us.doDelete(req, resp);
        ResultSet rs1 = us.getUserManager().getAll();
        boolean findUser = false;
        while(rs1.next()){
            if("slava".equals(rs1.getString("name"))){
                findUser = true;
                break;
            }
        }
        rs1.close();
        assertFalse(findUser);
        writer.close();
    }
}