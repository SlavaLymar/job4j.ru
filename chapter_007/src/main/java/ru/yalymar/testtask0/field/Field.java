package ru.yalymar.testtask0.field;

import java.util.Random;

/**
 * @author slavalymar
 * @since 16.04.2017
 * @version 1
 */
public class Field {

    /**
     * cells of field
     */
    private Cell[][] cells;

    /**
     * default size of field
     */
    private int size = 5;

    /**
     * value that determine count of monsters and count of walls
     */
    private final int value;

    /**
     * random function
     */
    private Random random = new Random();

    /**
     * flag to finish app
     */
    private boolean finish = false;

    // constructor if wants set field`s size
    public Field(int size) {
        if(size > 5){
            this.size = size;
        }
        this.value = this.size*this.size/5;
        this.createCells();
    }

    // default constructor
    public Field() {
        this.value = this.size*this.size/5;
        this.createCells();
    }

    /**
     * create field
     */
    private void createCells() {
        this.cells = new Cell[this.size][this.size];

        for (int i = 0; i<this.size; i++){
            for (int j = 0; j<this.size; j++){
                this.cells[i][j] = new AvailableCell();
            }
        }

        int count = this.value;
        while (count != 0) {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (count == 0) {
                        return;
                    }
                    if ((random.nextInt(10)) > 8 && this.cells[i][j].isAvailable()) {
                        this.cells[i][j] = new Wall();
                        count--;
                    }
                }
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public int getValue() {
        return this.value;
    }

    public Random getRandom() {
        return this.random;
    }

    public boolean isFinish() {
        return this.finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
