package ru.yalymar.jsp.controller;

import org.junit.Test;
import ru.yalymar.jsp.model.User;
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

public class EditUserServletTest {

    @Test
    public void whenEditUserShouldGetResultSetJSP() throws ServletException, IOException, SQLException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AddUserServlet us = new AddUserServlet();

        when(req.getParameter("name")).thenReturn("slava");
        when(req.getParameter("login")).thenReturn("slava123");
        when(req.getParameter("email")).thenReturn("slava123@gmail.nz");

        // add user
        us.doPost(req, resp);
        List<User> users = us.getUserManager().getAll();
        boolean[] findUser = new boolean[1];
        String[] id = new String[1];
        users.forEach((user)->{
            if("slava".equals(user.getName()) &&
                    "slava123".equals(user.getLogin()) &&
                    "slava123@gmail.nz".equals(user.getEmail())){
                findUser[0] = true;
                id[0] = user.getId();
                return;
            }
        });

        //edit user
        EditUserServlet es = new EditUserServlet();
        when(req.getParameter("name")).thenReturn("slavalymar");
        when(req.getParameter("id")).thenReturn(id[0]);
        es.doPost(req, resp);
        List<User> usersE = us.getUserManager().getAll();
        boolean[] findUserE = new boolean[1];
        usersE.forEach((user)->{
            if("slavalymar".equals(user.getName()) &&
                    "slava123".equals(user.getLogin()) &&
                    "slava123@gmail.nz".equals(user.getEmail())){
                findUserE[0] = true;
                return;
            }
        });
        assertTrue(findUserE[0]);

        //delete user
        DeleteUserServlet ds = new DeleteUserServlet();
        ds.doPost(req, resp);
        List<User> usersD = ds.getUserManager().getAll();
        boolean[] findUserD = new boolean[1];
        usersD.forEach((user)->{
            if("slava".equals(user.getName()) &&
                    "slava123".equals(user.getLogin()) &&
                    "slava123@gmail.nz".equals(user.getEmail())){
                findUserD[0] = true;
                return;
            }
        });
    }

}