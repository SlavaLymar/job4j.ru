package ru.paint;

/**
 * @author slavalymar
 * @since 02.01.2017
 * @version 1
 */
public class Paint {

    /**
     * @param h
     * @return sb.toString()
     */
    String pyramid(int h){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<h; i++){
            for(int j = 0; j<(h-i)-1; j++){
                sb.append(" ");
            }
            for(int k = 0; k<=i; k++){
                sb.append("^ ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Paint p = new Paint();
        System.out.println(p.pyramid(3));
    }
}
