package ru.lymar.testtask.field;

import ru.lymar.testtask.actions.Print;

public abstract class Field implements Print{

    private int size;
    private String[][] field;
    private String[] numbers;

    public Field(int size) {
        this.size = size;
        this.field = new String[this.size][this.size];
        this.numbers = new String[this.size];
    }

    public void fillFields(){
        for (int i = 0; i<this.field.length; i++){
            for (int j = 0; j<this.field.length; j++){
                this.field[i][j] = ".";
                this.numbers[i] = String.valueOf(i);
            }
        }
    }

    @Override
    public void print() {
        for (int k = 0; k<this.field.length; k++) {
            System.out.print(String.format("  %s", this.numbers[k]));
        }
        System.out.println(String.format("  %s", "Y"));

        for (int i = 0; i<this.field.length; i++){
            System.out.print(String.format("%s ", this.numbers[i]));
            for (int j = 0; j<this.field.length; j++){
                System.out.print(String.format("%s  ", this.field[i][j]));
            }
            System.out.println();
        }
        System.out.print(String.format("%s", "X"));
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
