package ru.yalymar.testtask0.model;

import ru.yalymar.testtask0.field.Field;

/**
 * @author slavalymar
 * @since 16.04.2017
 * @version 1
 */
public class Player extends Essence{

    public Player(Field field) {
        super("BOMBERMAN", field);
        this.createPlayer();
    }

    /**
     * create player
     */
    private void createPlayer() {
        int size = super.field.getSize();
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){
                if(super.field.getCells()[i][j].getE() == null &&
                        super.field.getCells()[i][j].isAvailable()){
                    super.field.getCells()[i][j].add(this);
                    super.setX(j);
                    super.setY(i);
                    return;
                }
            }
        }
    }

    /** create move
     * @return int[]
     */
    @Override
    public int[] createMove() {
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
                    continue;
                }
                super.move(newX, newY);
                success = true;
            }
        }
        while(!success);
        return new int[]{newX, newY};
    }

    public void playerAction() throws InterruptedException {
        //user`s input

        do{
            this.createMove();
            Thread.sleep(300);
        }
        while(!super.field.isFinish());

    }
}
