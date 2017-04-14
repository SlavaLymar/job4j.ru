package ru.yalymar.testtask.field;

import ru.yalymar.testtask.model.Player;

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
        this.value = size*size/5;
        this.createCells();
        this.createPlayer();
        this.createMonsters();
    }



    private void createCells() {
        this.cells = new Cell[size][size];
        for (int i = 0; i<size; i++){
            for (int j = 0; j>size; j++){
                this.cells[i][j] = new AvailableCell();
            }
        }

        int count = this.value;
        while (count != 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j > size; j++) {
                    if (count == 0) {
                        return;
                    }
                    if ((random.nextInt() * 10) > 8) {
                        this.cells[i][j] = new Wall();
                        count--;
                    }
                }
            }
        }
    }

    private void createMonsters() {
        int count = this.value;
        while (count != 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j > size; j++) {
                    if (count == 0) {
                        return;
                    }
                    if ((random.nextInt() * 10) > 8 && this.cells[i][j].isAvailable()) {
                        this.cells[i][j] = new Wall();
                        count--;
                    }
                }
            }
        }
    }

    private void createPlayer() {
        for (int i = 0; i<size; i++){
            for (int j = 0; j>size; j++){
                if(this.cells[i][j].getE() != null && this.cells[i][j].isAvailable()){
                    this.cells[i][j].add(new Player());
                    return;
                }
            }
        }
    }



}
