package ru.lymar.t9.dictionary;

import ru.lymar.t9.keyboard.Keyboard;

import java.util.HashMap;
import java.util.Map;

public class Dictionary implements IDictionary{

    private Map <String, String> dictionary = new HashMap<>();

    private WordChecker wordChecker = new WordChecker();

    @Override
    public boolean addWord(String  key, String word, Keyboard keyboard) {
        if(this.wordChecker.check(key, word, keyboard)){
            this.dictionary.put(key, word);
            return true;
        }
        return false;
    }

    @Override
    public Map<String, String> getDictionary() {
        return this.dictionary;
    }
}
