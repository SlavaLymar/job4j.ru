package ru.yalymar.filter.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yalymar.filter.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddControllerTest {

    private AddController us;

    @Before
    public void before() throws SQLException {
        us = new AddController();
        us.getUserManager().getDbManager().createUsersTable();
    }

    @After
    public void after() throws SQLException {
        us.getUserManager().getDbManager().dropUsersTable();
    }

    @Test
    public void whenAddUserShouldGetResultSetFILTER() throws ServletException, IOException, SQLException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        when(req.getParameter("login")).thenReturn("slava");
        when(req.getParameter("password")).thenReturn("slava123");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");
        when(req.getParameter("country")).thenReturn("rus");
        when(req.getParameter("city")).thenReturn("omsk");
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(("/WEB-INF/views/filter/mvcusers.jsp"))).thenReturn(rd);

        // add user
        us.doPost(req, resp);
        List<User> users = us.getUserManager().getAll();
        boolean findUser = users.stream().anyMatch((user)-> "slava".equals(user.getLogin()) &&
                "slava123".equals(user.getPassword()) &&
                "slava123@gmail.nz".equals(user.getEmail()));
        assertTrue(findUser);
    }

    @Test
    public void whenDeleteUserShouldGetResultSetFILTER() throws ServletException, IOException, SQLException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AddController us = new AddController();

        when(req.getParameter("login")).thenReturn("slava");
        when(req.getParameter("password")).thenReturn("slava123");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");
        when(req.getParameter("country")).thenReturn("rus");
        when(req.getParameter("city")).thenReturn("omsk");
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(("/WEB-INF/views/filter/mvcusers.jsp"))).thenReturn(rd);

        // add user
        us.doPost(req, resp);

        // find user
        List<User> users = us.getUserManager().getAll();
        String[] id = new String[1];
        boolean findUser = users.stream().anyMatch((user)->{
            if("slava".equals(user.getLogin()) &&
                    "slava123".equals(user.getPassword()) &&
                    "slava123@gmail.nz".equals(user.getEmail())){
                id[0] = user.getId();
                return true;
            }
            return false;
        });

        //delete user
        when(req.getParameter("id")).thenReturn(id[0]);
        DeleteController ds = new DeleteController();
        ds.doPost(req, resp);
        List<User> usersD = ds.getUserManager().getAll();
        boolean findUserD = usersD.stream().anyMatch((user)-> "slava".equals(user.getLogin()) &&
                "slava123".equals(user.getPassword()) &&
                "slava123@gmail.nz".equals(user.getEmail()));
        assertFalse(findUserD);
    }
}