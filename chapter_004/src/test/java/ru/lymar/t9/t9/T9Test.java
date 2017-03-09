package ru.lymar.t9.t9;

import org.junit.Test;
import ru.lymar.t9.button.Button;
import ru.lymar.t9.dictionary.Dictionary;
import ru.lymar.t9.keyboard.Keyboard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class T9Test {

    @Test
    public void whenEnterKeyThatHaveInDictionaryThenGetWord(){

        // init
        Keyboard keyboard = new Keyboard();
        keyboard.fillButtons();
        Dictionary dictionary = new Dictionary();
        T9 t9 = new T9(dictionary, keyboard);

        // add word to dictionary
        dictionary.addWord("3322", "hide", keyboard);

        // Using t9 to get the word by key
        String enteredKey = "3322";
        String result = t9.createWord(enteredKey);

        // assert block
        assertThat(result, is("hide"));
    }


    @Test
    public void whenEnter123ThenGenerateWords(){

        // init
        Keyboard keyboard = new Keyboard();
        keyboard.fillButtons();
        Dictionary dictionary = new Dictionary();
        T9 t9 = new T9(dictionary, keyboard);

        // create query
        String[] result = t9.generateForTwoButtons( new Button[]{keyboard.getBUTTONS()[0],
                keyboard.getBUTTONS()[1]});


        assertThat(result[0], is("12"));
        assertThat(result[1], is("1d"));

    }

}