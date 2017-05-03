package ru.yalymar.testtask0.model;

import ru.yalymar.testtask0.field.Field;

/**
 * @author slavalymar
 * @since 16.04.2017
 * @version 1
 */
public class Monster extends Essence{

    public Monster(String name, Field field) {
        super(name, field);
    }

    /** create move
     * @return int[]
     * @throws InterruptedException
     */
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

            // if it has another monster on the way - fall asleep in 500 ms
            if(super.field.getCells()[newY][newX].isAvailable()){
                if(super.field.getCells()[newY][newX].getE() != null &&
                        super.field.getCells()[newY][newX].getE().getName().contains("Monster")){
                    Thread.sleep(500);
                }

                // if is has bomberman kill him
                if(super.field.getCells()[newY][newX].getE() != null &&
                        super.field.getCells()[newY][newX].getE().getName().contains("BOMBERMAN")){

                    System.out.println("Bomberman has dead!");
                    super.field.setFinish(true);
                }
                super.move(newX, newY);
                success = true;
            }
        }
        while(!success);
        return new int[]{newX, newY};
    }
}
