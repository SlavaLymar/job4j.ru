package ru.lymar.testtask.field;

/**
 * @author slavalymar
 * @since 27.02.2017
 * @version 1
 */
public class Cell {

    private int x;
    private int y;
    private String value;

    public Cell(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /** get value
     * @return String
     */
    public String getValue() {
        return value;
    }

    /** set value
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
