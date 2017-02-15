package ru.lymar.srp;

import org.junit.Test;
import ru.lymar.srp.output.Output;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AppTest {

    private App app;

    public void init(){
        this.app = new App();
        app.init();
    }

    @Test
    public void initTest() {

    }

    @Test
    public void mainLoopTest() {

    }

    @Test
    public void getPartsTest() {

    }

    @Test
    public void calculateOfTwoTest() {
        this.init();
        app.calculateOfTwo("11+11");
        assertThat(app.getOutput().getResult(), is(22.0F));
    }

    @Test
    public void calculateContinue() {
        this.calculateOfTwoTest();
        app.calculateContinue("+33");
        assertThat(app.getOutput().getResult(), is(55.0F));
    }

    @Test
    public void calculateOneTest() {
        this.calculateOfTwoTest();
        app.calculateOne("16");
        assertThat(app.getOutput().getResult(), is(16.0F));
    }

}