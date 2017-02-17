package ru.lymar.srp;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AppTest {

    private App app;

    public void init(){
        this.app = new App();
        app.init();
    }

    @Test
    public void mainLoopTest() {
        this.init();
        app.mainLoop();
    }

}