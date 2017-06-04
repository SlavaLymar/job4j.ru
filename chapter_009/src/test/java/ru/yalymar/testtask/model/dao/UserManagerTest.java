package ru.yalymar.testtask.model.dao;

import org.junit.Before;
import org.junit.Test;
import ru.yalymar.testtask.model.TypeOfMusic;
import ru.yalymar.testtask.model.User;
import java.sql.Timestamp;
import java.util.Calendar;
import static org.junit.Assert.assertTrue;

public class UserManagerTest {

    private DAOFabric daoFabric;
    private UserManager manager;

    @Before
    public void init(){
        this.daoFabric = new DAOFabric();
        this.manager = this.daoFabric.getUserManager();
    }

    @Test
    public void whenAddTypeOfMusicShouldGetId(){
        User user = new User("test2", "test2", "test2",
                new Timestamp(Calendar.getInstance().getTimeInMillis()),
                "user", "Test2 st. ap test2");
        user.getTypes().add(new TypeOfMusic("rock"));
        user.getTypes().add(new TypeOfMusic("rap"));
        user.getTypes().add(new TypeOfMusic("folk"));

        int id = this.manager.create(user);
        assertTrue(id > 0);

        this.manager.remove(id);
    }



}