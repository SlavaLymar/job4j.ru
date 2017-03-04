package ru.lymar.t9.dictionary;

import ru.lymar.t9.button.Button;
import ru.lymar.t9.keyboard.Keyboard;

public class WordChecker implements ICheckWord{

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
