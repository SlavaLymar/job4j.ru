package ru.lymar.srp;

import ru.lymar.srp.calculate.Addiction;
import ru.lymar.srp.calculate.Divide;
import ru.lymar.srp.calculate.Multiply;
import ru.lymar.srp.calculate.Subtract;
import ru.lymar.srp.input.Input;
import ru.lymar.srp.output.Output;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class App {

    private Output output;
    private Input input;

    /**
     * constant for exit
     */
    private final String EXIT = "exit";

    /** getter
     * @return
     */
    public Output getOutput() {
        return output;
    }

    /**
     * initialization
     */
    public void init(){
        this.output = new Output();
        this.input = new Input(new Scanner(System.in));
        this.output.writeGreetings();
    }

    /**
     * loop of app
     */
    public void mainLoop(){
        String str;
        do {
            this.output.writeEnter();
            str = this.input.readConsole();
            this.getParts(str);
            this.output.writeResult();
        }
        while(!EXIT.equals(str));
    }

    /** check type of calculation
     * @param str
     */
    public void getParts(String str){
        String s = str.replace(" ", "");
        Pattern twoNumbers = Pattern.compile("\\d+[\\D]\\d+");
        Matcher matcherOfTwoNumbers = twoNumbers.matcher(s);

        Pattern oneNumber = Pattern.compile("[\\D]\\d+");
        Matcher matcherOfOneNumber = oneNumber.matcher(s);

        Pattern justNumber = Pattern.compile("\\d+");
        Matcher matcherOfJustNumber = justNumber.matcher(s);

        if(matcherOfTwoNumbers.find()){
            this.calculateOfTwo(s);
        }
        else if(matcherOfOneNumber.find()){
            this.calculateContinue(s);
        }
        else if(matcherOfJustNumber.find()){
            this.calculateOne(s);
        }
    }

    /** calculation of the two values
     * @param s
     */
    public void calculateOfTwo(String s){
        String[] s1 = s.split("\\D");
        if(s.contains("+")){
            Calculator calc = new Calculator(new Addiction());
            this.output.setResult(calc.action(Float.valueOf(s1[0]),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("-")){
            Calculator calc = new Calculator(new Subtract());
            this.output.setResult(calc.action(Float.valueOf(s1[0]),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("*")){
            Calculator calc = new Calculator(new Multiply());
            this.output.setResult(calc.action(Float.valueOf(s1[0]),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("/")){
            Calculator calc = new Calculator(new Divide());
            this.output.setResult(calc.action(Float.valueOf(s1[0]),
                    Float.valueOf(s1[1])));
        }
    }

    /** continue calculation
     * @param s
     */
    public void calculateContinue(String s){
        String[] s1 = s.split("\\D");
        if(s.contains("+")){
            Calculator calc = new Calculator(new Addiction());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("-")){
            Calculator calc = new Calculator(new Subtract());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("*")){
            Calculator calc = new Calculator(new Multiply());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("/")){
            Calculator calc = new Calculator(new Divide());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1[1])));
        }
    }

    /** equated value to result
     * @param s
     */
    public void calculateOne(String s){
        this.output.setResult(Float.valueOf(s));
    }

    /** point of entry in app
     * @param args
     */
    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.mainLoop();
    }


}
