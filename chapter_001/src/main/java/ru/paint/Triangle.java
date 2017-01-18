package ru.paint;


/**
 * @author slavalymar
 * @since 18.01.2017
 * @version 1
 */
public class Triangle extends Paint{

    private int x;

    /**
     * @param x
     */
    public Triangle(int x) {
        this.x = x;
    }

    /** create triangle
     * @return String
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<x; i++){
            for(int j = 0; j<(x-i)-1; j++){
                sb.append(" ");
            }
            for(int k = 0; k<=i; k++){
                sb.append(". ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * draw triangle
     */
    @Override
    public void draw() {
        System.out.println(pic());
    }
}
