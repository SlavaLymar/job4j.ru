package ru.lymar.testtask.field;

import ru.lymar.testtask.actions.Print;
import ru.lymar.testtask.actions.Move;
import ru.lymar.testtask.actions.WinChecked;
import ru.lymar.testtask.exception.CellIsOccupiedException;
import ru.lymar.testtask.player.Player;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public abstract class Field implements Print, Move, WinChecked{

    /**
     * size of field
     */
    private int size;

    /**
     * array of cells
     */
    private Cell[][] cells;

    /**
     * array of numbers
     */
    private String[] numbers;

    public Field(int size) {
        this.size = size;
        this.cells = new Cell[this.size][this.size];
        this.numbers = new String[this.size];
    }

    /** get cells array
     * @return Cell[][]
     */
    public Cell[][] getCells() {
        return this.cells;
    }

    /**
     * fill field
     */
    public void fillFields(){
        for (int i = 0; i<this.cells.length; i++){
            for (int j = 0; j<this.cells.length; j++){
                this.cells[i][j] = new Cell(i, j, ".");
                this.numbers[i] = String.valueOf(i);
            }
        }
    }

    /**
     * print field
     */
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
        System.out.println(String.format("%s", "X"));
    }

    /** make a move
     * @param x
     * @param y
     * @param player
     * @return boolean
     * @throws CellIsOccupiedException
     */
    @Override
    public boolean move(int x, int y, Player player) throws CellIsOccupiedException{
        boolean result = false;
        if(this.isFreeCell(x, y)) {
            result = true;
            this.cells[x][y].setValue(player.getFlag());
        }
        else {
            throw new CellIsOccupiedException("The cell is occupied!");
        }
        return result;
    }

    /** checked is cell free
     * @param x
     * @param y
     * @return boolean
     */
    public boolean isFreeCell(int x, int y){
        if(this.cells[x][y].getValue().equals(".")) return true;
        return false;
    }

    /** checked win
     * @param player
     * @return boolean
     */
    public boolean winChecked(Player player){
        if(this.winCheckedLine(player) || this.winCheckedDiagonal(player)) {
            return true;
        }
        return false;
    }

    /** checked line for win
     * @param player
     * @return boolean
     */
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

    /** checked diagonal for win
     * @param player
     * @return boolean
     */
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
