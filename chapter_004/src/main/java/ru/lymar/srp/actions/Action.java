package ru.lymar.srp.actions;

import ru.lymar.srp.calculate.*;
import ru.lymar.srp.output.Output;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class Action {

    /**
     * list of first actions
     */
    private FirstAction[] actions;

    /**
     * list of available calculations
     */
    private Calculate[] calculates;
    private int position = 0;
    private Output output;
    private int i;

    public Action(Output output) {
        this.actions = new FirstAction[4];
        this.calculates = new Calculate[8];
        this.output = output;
        this.fillActions();
        this.fillCalculates();
    }

    /** select first action according to patterns
     * @param str
     */
    public void selectFirstAction(String str){
        String s = str.replace(" ", "");

        String[] patterns = {"^\\d+[\\D]\\d+$", "^[\\D]\\d+$", "^\\d+$", "^.{2,4}\\d+$"};
        this.i = 0;
        for(String pat : patterns){
            Pattern pattern = Pattern.compile(pat);
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                this.actions[i].select(s);
                break;
            }
            this.i++;
        }
    }

    /** get i for test select method
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     * fill actions[]
     */
    private void fillActions(){
        this.actions[this.position++] = new ArithmeticOfTwoNumbers();
        this.actions[this.position++] = new ArithmeticOfOneNumber();
        this.actions[this.position++] = new ArithmeticOne();
        this.actions[this.position++] = new Trigonometry();
        this.position = 0;
    }

    /**
     * fill calculates[]
     */
    private void fillCalculates() {
        this.calculates[this.position++] = new Addiction();
        this.calculates[this.position++] = new Subtract();
        this.calculates[this.position++] = new Multiply();
        this.calculates[this.position++] = new Divide();
        this.calculates[this.position++] = new Sinus();
        this.calculates[this.position++] = new Cosine();
        this.calculates[this.position++] = new Tangent();
        this.calculates[this.position++] = new Cotangent();
    }

    /**
     * inner class for calculate the two numbers
     */
    private class ArithmeticOfTwoNumbers implements FirstAction {

        /** execute calculation for the two numbers
         * @param s
         */
        @Override
        public void select(String s) {
            String[] s1 = s.split("\\D");
            if (s.contains("+")) {
                float result = calculates[0].execute(Float.valueOf(s1[0]),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
            if (s.contains("-")) {
                float result = calculates[1].execute(Float.valueOf(s1[0]),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
            if (s.contains("*")) {
                float result = calculates[2].execute(Float.valueOf(s1[0]),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
            if (s.contains("/")) {
                float result = calculates[3].execute(Float.valueOf(s1[0]),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
        }
    }


    /**
     * inner class for calculate one number
     */
    private class ArithmeticOfOneNumber implements FirstAction {

        /** execute calculation on
         * @param s
         */
        @Override
        public void select(String s) {
            String[] s1 = s.split("\\D");
            if(s.contains("+")){
                float result = calculates[0].execute(output.getResult(),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
            if(s.contains("-")){
                float result = calculates[1].execute(output.getResult(),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
            if(s.contains("*")){
                float result = calculates[2].execute(output.getResult(),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
            if(s.contains("/")){
                float result = calculates[3].execute(output.getResult(),
                        Float.valueOf(s1[1]));
                output.setResult(result);
            }
        }
    }


    /**
     * inner class for one number
     */
    private class ArithmeticOne implements FirstAction {

        /** equating to the result
         * @param s
         */
        @Override
        public void select(String s) {
            output.setResult(Float.valueOf(s));
        }
    }

    /**
     * inner class for trigonometry
     */
    private class Trigonometry implements FirstAction {

        /** execute calculation
         * @param s
         */
        @Override
        public void select(String s) {
            String[] s1 = s.split("\\D+");
            if(s.contains("sin")){
                float result = calculates[4].execute(Float.valueOf(s1[1]),
                        0);
                output.setResult(result);
            }
            if(s.contains("cos")){
                float result = calculates[5].execute(Float.valueOf(s1[1]),
                        0);
                output.setResult(result);
            }
            if(s.contains("tg")){
                float result = calculates[6].execute(Float.valueOf(s1[1]),
                        0);
                output.setResult(result);
            }
            if(s.contains("ctg")){
                float result = calculates[7].execute(Float.valueOf(s1[1]),
                        0);
                output.setResult(result);
            }
        }
    }


}
