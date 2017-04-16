package ru.yalymar.testtask.model;

import ru.yalymar.testtask.field.Field;
import java.util.Random;

public class Monsters {

    private Monster[] monsters;
    private Random random = new Random();
    private Field field;

    public Monsters(Field field) {
        this.field = field;
        this.monsters = new Monster[field.getValue()];
        this.createMonsters();
    }

    public Monster[] getMonsters() {
        return this.monsters;
    }

    private void createMonsters() {
        int count = this.monsters.length;
        int size = this.field.getSize();
        while (count != 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (count == 0) {
                        return;
                    }
                    if ((this.random.nextInt(10)) > 6 &&
                            this.field.getCells()[i][j].isAvailable() &&
                            this.field.getCells()[i][j].getE() == null) {
                        this.field.getCells()[i][j].add(new Monster(String.format("Monster %d", count), this.field));
                        this.monsters[count-1] = (Monster) this.field.getCells()[i][j].getE();
                        this.field.getCells()[i][j].getE().setX(j);
                        this.field.getCells()[i][j].getE().setY(i);
                        count--;
                    }
                }
            }
        }
    }

    public void monstersActions() throws InterruptedException {
        while (this.monsters.length > 0){
            for(Monster m : this.monsters){
                m.createMove();
                Thread.sleep(300);
            }
        }
    }

}
