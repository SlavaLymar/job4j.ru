package ru.yalymar.polyndrom;

/**
 * @author slavalymar
 * @since 29.01.2017
 * @version 1
 */
public class IsPolyndrome {

    public boolean isPolyndrome(String str) throws NotFiveException{
        boolean result = false;
        int strLength = str.length();
        String strLowerCase = str.toLowerCase();
        if(strLength == 5){
            for(int i = 0; i<((strLength/2)-1); i++){
                if(strLowerCase.charAt(i) == strLowerCase.charAt(strLength-(i+1))){
                    result = true;
                    continue;
                }
                else {
                    result = false;
                    break;
                }
            }
        }
        else throw new NotFiveException("You have to enter 5 characters!");
        return result;
    }
}
