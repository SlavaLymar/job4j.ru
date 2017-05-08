package ru.yalymar.testtask0;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StartAppTest {

    @Test
    public void whenLoadPropertiesShouldGetIt(){
        StartApp startApp = new StartApp();
        startApp.initProperties();
        String url = startApp.getProperties().getProperty("url");
        assertThat(url, is("http://www.sql.ru/forum/job-offers"));

        String urlConnect = startApp.getProperties().getProperty("urlConnect");
        assertThat(urlConnect, is("jdbc:postgresql://localhost:5432/job-offers"));
    }

}