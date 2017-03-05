package ru.lymar.t9.dictionary;

import ru.lymar.t9.keyboard.Keyboard;
import java.util.HashMap;
import java.util.Map;

/**
 * @author slavalymar
 * @since 05.03.2017
 * @version 1
 */
public class Dictionary implements IDictionary{

    /**
     * dictionary
     */
    private Map <String, String> dictionary = new HashMap<>();

    /**
     * class that checks that the word matches the key
     */
    private WordChecker wordChecker = new WordChecker();

    /** add word to dictionary
     * @param key
     * @param word
     * @param keyboard
     * @return boolean
     */
    @Override
    public boolean addWord(String  key, String word, Keyboard keyboard) {
        if(this.wordChecker.check(key, word, keyboard)){
            this.dictionary.put(key, word);
            return true;
        }
        return false;
    }

    /** get dictionary
     * @return Map
     */
    @Override
    public Map<String, String> getDictionary() {
        return this.dictionary;
    }
}
