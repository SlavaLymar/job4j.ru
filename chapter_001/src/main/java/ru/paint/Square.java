package ru.paint;

/**
 * @author slavalymar
 * @since 18.01.2017
 * @version 1
 */
public class Square extends Paint{

    private int x;

    /**
     * @param x
     */
    public Square(int x) {
        this.x = x;
    }

    /** create square
     * @return String
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<x; i++){
            for(int j = 0; j<x; j++){
                sb.append(".  ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * draw square
     */
    @Override
    public void draw() {
        System.out.println(pic());
    }
}
