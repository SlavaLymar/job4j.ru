package ru.yalymar.testtask0;

import org.apache.log4j.Logger;
import ru.yalymar.testtask0.field.Field;
import ru.yalymar.testtask0.model.Monsters;
import ru.yalymar.testtask0.model.Player;

public class Main {

    private Field field;
    private Player player;
    private Monsters monsters;
    public static Logger log = Logger.getLogger(Main.class);

    public void init() throws InterruptedException {
        this.field = new Field();
        this.player = new Player(this.field);
        this.monsters = new Monsters(this.field);
        this.game();
    }

    private void game() throws InterruptedException {

        // monster actions
        Thread t1 = new Thread(){
            @Override
            public void run() {
                this.setName("Monsters` Thread");
                try {
                    log.info("Start thread 1 - monsters` actions");
                    monsters.monstersActions();
                } catch (InterruptedException e) {
                    log.info("Stop thread 1 - monsters` actions");
                }
            }
        };
        t1.start();

        // player actions
        Thread t2 = new Thread(){
            @Override
            public void run() {
                this.setName("Monsters` Thread");
                try {
                    log.info("Start thread 2 - player`s actions");
                    player.playerAction();
                } catch (InterruptedException e) {
                    log.info("Stop thread 2 - player`s actions");
                }
            }
        };
        t2.start();

        t1.join();
        t2.join();
        log.info("Game Over!");
        System.out.println("Game Over");
    }

    public static void main(String[] args) throws InterruptedException {
        log.info("Start Game");
        System.out.println("Start Game");
        new Main().init();
    }
}
