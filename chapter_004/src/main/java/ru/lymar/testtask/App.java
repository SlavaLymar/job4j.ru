package ru.lymar.testtask;

import ru.lymar.testtask.exception.CellIsOccupiedException;
import ru.lymar.testtask.field.Field;
import ru.lymar.testtask.field.GameField;
import ru.lymar.testtask.player.Player;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class App {

    private Field field;
    private Player player;
    private Setting setting;

    /**
     * fill settings
     */
    public void fillSettings(){
        this.setting = new Setting();
        this.setting.askSize();
        this.setting.askFirstStep();
    }

    /**
     * initialized
     */
    public void init(){
        this.fillSettings();
        this.field = new GameField(this.setting.getSize());
        this.field.fillFields();
        this.field.print();
        this.mainLoop();
    }

    /**
     * main loop
     */
    public void mainLoop(){
        // loop depends of size of field
        for(int i = 0; i <Math.pow(this.setting.getSize(), 2); i++){

            // set player
            this.player = this.setting.choosePlayer(i%2);

            // It determines whether the move was made
            boolean moveDone = true;
            do {
                try {
                    this.field.move(this.player.getNumber("Enter X: "),
                            this.player.getNumber("Enter Y: "), this.player);
                    moveDone = false;
                    this.field.print();

                } catch (CellIsOccupiedException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (moveDone);

            // checked win
            if(this.field.winChecked(this.player)){
                this.winMsg();
                break;
            }

        }
        this.exitMsg();

    }

    /**
     * write win message
     */
    private void winMsg() {
        String msg = String.format("%s%s", this.player.getName(), " wins!");
        System.out.println(msg);
    }

    /**
     * write exit message
     */
    private void exitMsg() {
        System.out.println("Game over!");
    }

    /** point of entry
     * @param args
     */
    public static void main(String[] args) {
        new App().init();
    }


}
