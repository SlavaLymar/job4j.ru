package ru.yalymar.checkbyte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author slavalymar
 * @since 25.01.2017
 * @version 1
 */
public class CheckByte{

    /**
     * @param in
     * @return boolean
     */
    public boolean isNumber(InputStream in){

        boolean result = false;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(in))){
            int c;
            do {
                c = br.read();
                if (c % 2 == 0) {
                    result = true;
                    System.out.println(c + " - четное");
                } else System.out.println(c + " - нечетное");
                System.out.println();
            }
            while (c != 10);
        }
        catch (IOException e){
            System.out.println("Incorrect enter!");
        }

        return result;
    }
}

