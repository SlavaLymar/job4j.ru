package ru.lymar.srp.actions;

import org.junit.Test;
import ru.lymar.srp.output.Output;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class ActionTest {

    private Output output;
    private Action action;

    public void init(){
        this.output = new Output();
        this.action = new Action(output);
    }

    @Test
    public void selectFirstActionTwoNumbersTest(){
        this.init();
        action.selectFirstAction("2+2");
        assertThat(action.getI(), is(0));
    }

    @Test
    public void arithmeticOfTwoNumbersTest(){
        this.init();
        this.selectFirstActionTwoNumbersTest();
        assertThat(this.output.getResult(), is(4.0F));
    }

    @Test
    public void selectFirstActionOneNumbersTest(){
        this.init();
        action.selectFirstAction("+2");
        assertThat(action.getI(), is(1));
    }

    @Test
    public void arithmeticOfOneNumbersTest(){
        this.init();
        this.selectFirstActionOneNumbersTest();
        assertThat(this.output.getResult(), is(2.0F));
    }

    @Test
    public void selectFirstActionOneTest(){
        this.init();
        action.selectFirstAction("2");
        assertThat(action.getI(), is(2));
    }

    @Test
    public void arithmeticOfOneTest(){
        this.init();
        this.selectFirstActionOneTest();
        assertThat(this.output.getResult(), is(2.0F));
    }

    @Test
    public void selectFirstActionTrigonomertyTest(){
        this.init();
        action.selectFirstAction("cos180");
        assertThat(action.getI(), is(3));
    }

    @Test
    public void arithmeticOfTrigonometryTest(){
        this.init();
        this.selectFirstActionTrigonomertyTest();
        assertThat(this.output.getResult(), is(-1.0F));
    }

}