package ru.lymar.srp;

import ru.lymar.srp.actions.Action;
import ru.lymar.srp.input.Input;
import ru.lymar.srp.output.Output;
import java.util.Scanner;

/**
 * @author slavalymar
 * @since 15.02.2017
 * @version 1
 */
public class App {

    private Output output;
    private Input input;
    private Action action;

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
        this.action = new Action(this.output);
    }

    /**
     * loop of app
     */
    public void mainLoop(){
        String str;
        do {
            this.output.writeEnter();
            str = this.input.readConsole();
            action.selectFirstAction(str);
            this.output.writeResult();
        }
        while(!EXIT.equals(str));
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
