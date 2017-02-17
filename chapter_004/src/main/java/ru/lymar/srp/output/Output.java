package ru.lymar.srp.output;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Output {

    /**
     * result value
     */
    private float result = 0;

    /** getter
     * @return float
     */
    public float getResult() {

        return this.result;
    }

    /** setter
     * @param result
     */
    public void setResult(float result) {
        this.result = result;
    }

    /**
     * write greetings
     */
    public void writeGreetings() {

        String str = String.format("%s%s%s%s%s%s%s%s%s%s", "Welcome!", System.getProperty("line.separator"),
                "Enter eg. 2+2", System.getProperty("line.separator"),
                "For use result enter eg. +2", System.getProperty("line.separator"),
                "For exit write 'exit'", System.getProperty("line.separator"),
                "For trigonometry use keys: sin, cos, tg, ctg (eg. sin90)", System.getProperty("line.separator"));
        System.out.println(str);
    }

    /**
     * write enter
     */
    public void writeEnter() {

        System.out.println("Enter expression: ");
    }

    /**
     * write result
     */
    public void writeResult() {
        String str = String.format("%s%s", "Result: ", this.result);
        System.out.println(str);
    }
}
