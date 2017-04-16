package ru.yalymar.testtask.field;

import java.util.Random;

public class Field {

    private Cell[][] cells;
    private int size = 5;
    private final int value;
    private Random random = new Random();

    public Field(int size) {
        if(size > 5){
            this.size = size;
        }
        this.value = this.size*this.size/5;
        this.createCells();
    }

    public Field() {
        this.value = this.size*this.size/5;
        this.createCells();
    }

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
}
