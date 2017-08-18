package ru.yalymar.mvc.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yalymar.mvc.model.models.Color;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc-context-test.xml")
public class Test1 {

    @Autowired
    private DAOFactory daoFactory;

    @Test
    public void wh(){
        Color color = new Color();
        color.setColor("black");
        this.daoFactory.getColorDAO().create(color);
        System.out.println();
    }
}
