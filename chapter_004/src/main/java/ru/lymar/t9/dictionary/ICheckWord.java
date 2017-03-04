package ru.lymar.t9.dictionary;

import ru.lymar.t9.keyboard.Keyboard;

public interface ICheckWord {

    boolean check(String key, String word, Keyboard keyboard);
}
