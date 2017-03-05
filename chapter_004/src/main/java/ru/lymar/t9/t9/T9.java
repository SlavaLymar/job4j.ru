package ru.lymar.t9.t9;

import ru.lymar.t9.button.Button;
import ru.lymar.t9.dictionary.Dictionary;
import ru.lymar.t9.keyboard.Keyboard;

/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public class T9 implements It9{

    /**
     * dictionary
     */
    private Dictionary dictionary;

    /**
     * keyboard
     */
    private Keyboard keyboard;

    public T9(Dictionary dictionary, Keyboard keyboard) {
        this.dictionary = dictionary;
        this.keyboard = keyboard;
    }

    /** get word from dictionary according to key
     * @param key
     * @return String
     */
    @Override
    public String createWord(String key) {
        if(this.dictionary.getDictionary().containsKey(key)){
            return this.dictionary.getDictionary().get(key);
        }
        else {
            String word = "";
            for(int i = 0; i < key.length(); i++){
                char[] charsOfButton = null;
                for(Button button : keyboard.getBUTTONS()){
                    if(button.getName().charAt(0) == key.charAt(i)){
                        charsOfButton = button.getCharacters();
                        break;
                    }
                    else{
                        word.concat(String.valueOf(key.charAt(i)));
                        continue;
                    }
                }
                word.concat(String.valueOf(charsOfButton[0]));
                continue;
            }
            return word;
        }


    }


}
