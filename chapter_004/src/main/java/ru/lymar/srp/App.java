package ru.lymar.srp;

import ru.lymar.srp.calculate.Addiction;
import ru.lymar.srp.calculate.Divide;
import ru.lymar.srp.calculate.Multiply;
import ru.lymar.srp.calculate.Substract;
import ru.lymar.srp.input.Input;
import ru.lymar.srp.output.Output;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    private Output output;
    private Input input;
    private final String EXIT = "exit";

    public Output getOutput() {
        return output;
    }

    public void init(){
        this.output = new Output();
        this.input = new Input();
        this.output.writeGreetings();
    }

    public void mainLoop(){
        do {
            this.output.writeEnter();
            this.getParts(this.input.readConsole());
        }
        while(EXIT.equals(this.input.readConsole()));
    }

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

    public void calculateOfTwo(String s){
        String[] s1 = s.split("\\D");
        if(s.contains("+")){
            Calculator calc = new Calculator(new Addiction());
            this.output.setResult(calc.action(Float.valueOf(s1[0]),
                    Float.valueOf(s1[1])));
        }
        if(s.contains("-")){
            Calculator calc = new Calculator(new Substract());
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

    public void calculateContinue(String s){
        String s1 = s.replace("\\D", "");
        if(s.contains("+")){
            Calculator calc = new Calculator(new Addiction());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1)));
        }
        if(s.contains("-")){
            Calculator calc = new Calculator(new Substract());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1)));
        }
        if(s.contains("*")){
            Calculator calc = new Calculator(new Multiply());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1)));
        }
        if(s.contains("/")){
            Calculator calc = new Calculator(new Divide());
            this.output.setResult(calc.action(this.output.getResult(),
                    Float.valueOf(s1)));
        }
    }

    public void calculateOne(String s){
        this.output.setResult(Float.valueOf(s));
    }

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.mainLoop();
    }


}
