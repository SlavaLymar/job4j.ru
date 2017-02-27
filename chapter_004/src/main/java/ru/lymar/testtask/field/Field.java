package ru.lymar.testtask.field;

import ru.lymar.testtask.actions.Print;
import ru.lymar.testtask.actions.Turn;
import ru.lymar.testtask.actions.WinChecked;
import ru.lymar.testtask.exception.CellIsOccupiedException;
import ru.lymar.testtask.player.Player;

public abstract class Field implements Print, Turn, WinChecked{

    private int size;
    private Cell[][] cells;
    private String[] numbers;

    public Field(int size) {
        this.size = size;
        this.cells = new Cell[this.size][this.size];
        this.numbers = new String[this.size];
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public void fillFields(){
        for (int i = 0; i<this.cells.length; i++){
            for (int j = 0; j<this.cells.length; j++){
                this.cells[i][j] = new Cell(i, j, ".");
                this.numbers[i] = String.valueOf(i);
            }
        }
    }

    @Override
    public void print() {
        for (int k = 0; k<this.cells.length; k++) {
            System.out.print(String.format("  %s", this.numbers[k]));
        }
        System.out.println(String.format("  %s", "Y"));

        for (int i = 0; i<this.cells.length; i++){
            System.out.print(String.format("%s ", this.numbers[i]));
            for (int j = 0; j<this.cells.length; j++){
                System.out.print(String.format("%s  ", this.cells[i][j].getValue()));
            }
            System.out.println();
        }
        System.out.print(String.format("%s", "X"));
    }

    @Override
    public void turn(int x, int y, Player player) throws CellIsOccupiedException {
        if(this.isFreeCell(x, y)) {
            this.cells[x][y].setValue(player.getFlag());
        }
        else throw new CellIsOccupiedException("The cell is occupied!");
    }

    public boolean isFreeCell(int x, int y){
        if(this.cells[x][y].getValue().equals(".")) return true;
        return false;
    }

    public boolean winChecked(Player player){
        if(this.winCheckedLine(player) || this.winCheckedDiagonal(player)) {
            System.out.println(String.format("%s %s", player.getName(), "wins!"));
            return true;
        }
        return false;
    }

    @Override
    public boolean winCheckedLine(Player player) {
        int count;
        for (int i = 0; i < this.size; i++) {
            count = 0;
            for (int j = 0; j < this.size; j++) {
                if (this.cells[i][j] != null
                        && this.cells[i][j].getValue().equals(player.getFlag())) {
                    count++;
                }
            }
            if(count == this.size) { return true; }
        }
        return false;
    }

    @Override
    public boolean winCheckedDiagonal(Player player) {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.cells[i][i] != null
                    && this.cells[i][i].getValue().equals(player.getFlag())) {
                count++;
            }
        }
        if(count == this.size) { return true; }

        count = 0;
        for (int i = 0, j = this.size-1; i < this.size; i++, j--) {
            if (this.cells[i][j] != null
                    && this.cells[i][j].getValue().equals(player.getFlag())) {
                count++;
            }
        }
        if(count == this.size) { return true; } else { return false;}
    }
}
