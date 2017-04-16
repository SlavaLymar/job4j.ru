package ru.yalymar.testtask.model;

import ru.yalymar.testtask.field.Field;

public class Monster extends Essence{

    public Monster(String name, Field field) {
        super(name, field);
    }

    @Override
    public int[] createMove() throws InterruptedException {
        boolean success = false;
        int newX;
        int newY;
        do {
            newX = this.x + super.r.get();
            newY = this.y + super.r.get();
            if((newX > this.field.getSize()-1 || newX < 0) ||
                    (newY > this.field.getSize()-1 || newY < 0)){
                continue;
            }
            if(newX == this.x && newY == this.y){
                continue;
            }

            if(super.field.getCells()[newY][newX].isAvailable()){
                if(super.field.getCells()[newY][newX].getE() != null &&
                        super.field.getCells()[newY][newX].getE().getName().contains("Monster")){
                    Thread.sleep(500);
                }
                if(super.field.getCells()[newY][newX].getE() != null &&
                        super.field.getCells()[newY][newX].getE().getName().contains("BOMBERMAN")){
                    super.field.getCells()[newY][newX].getE().setAlive(false);
                }
                super.move(newX, newY);
                success = true;
            }
        }
        while(!success);
        return new int[]{newX, newY};
    }
}
