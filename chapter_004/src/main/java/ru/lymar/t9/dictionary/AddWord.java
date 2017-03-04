package ru.lymar.t9.dictionary;

import ru.lymar.t9.keyboard.Keyboard;

public interface AddWord {

    boolean addWord(String key, String word, Keyboard keyboard);
}
