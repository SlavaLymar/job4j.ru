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
            String str;
            int i;
            while(br.ready()) {
                str = br.readLine();
                if (Integer.valueOf(str) % 2 == 0) {
                    result = true;
                    System.out.println(str+" is a even");
                }
                else System.out.println(str+" is a odd");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }
}

