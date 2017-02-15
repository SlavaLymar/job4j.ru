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

    @Test
    public void getPartsTest() {
        this.init();
        String str = "-20";
        app.getParts(str);
        assertThat(app.getOutput().getResult(), is(-20.0F));
    }

    @Test
    public void calculateTrigonometryTest(){
        this.init();
        app.calculateTrigonometry("sin90");
        assertThat(app.getOutput().getResult(), is(1.0F));
    }

    @Test
    public void calculateOfTwoTest() {
        this.init();
        app.calculateOfTwo("11/11");
        assertThat(app.getOutput().getResult(), is(1.0F));
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