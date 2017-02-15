package ru.lymar.srp.output;

public class Output {

    private float result = 0;

    public float getResult() {
        return this.result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public void writeGreetings() {

        String str = String.format("%s%s%s%s%s%s%s%s", "Welcome!", System.getProperty("line.separator"),
                "Enter eg. 2+2", System.getProperty("line.separator"),
                "For use result enter eg. +2", System.getProperty("line.separator"),
                "For exit write 'exit'", System.getProperty("line.separator"));
        System.out.println(str);
    }

    public void writeEnter() {
        System.out.print("Enter expression: ");
    }
}
