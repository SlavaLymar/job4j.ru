package ru.lymar.t9.dictionary;

import ru.lymar.t9.button.Button;
import ru.lymar.t9.keyboard.Keyboard;

/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public class WordChecker implements ICheckWord{

    /** check that the word matches the key
     * @param key
     * @param word
     * @param keyboard
     * @return boolean
     */
    @Override
    public boolean check(String key, String word, Keyboard keyboard) {
        boolean result = false;
        for(int i = 0; i < key.length(); i++){
            char[] charsOfButton = null;
            for(Button button : keyboard.getBUTTONS()){
                if(button.getName().charAt(0) == key.charAt(i)){
                    charsOfButton = button.getCharacters();
                    break;
                }
                else {
                    result = false;
                }
            }
            for(char c : charsOfButton){
                if(c == word.charAt(i)) {
                    result = true;
                    break;
                }
                else {
                    result = false;
                }
            }
        }
        return result;
    }

}
