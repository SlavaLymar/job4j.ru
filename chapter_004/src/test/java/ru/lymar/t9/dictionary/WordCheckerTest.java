package ru.lymar.t9.dictionary;

import org.junit.Test;
import ru.lymar.t9.keyboard.Keyboard;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WordCheckerTest {

    @Test
    public void whenAddCorrectWordToDictionary() {
        Keyboard keyboard = new Keyboard();
        keyboard.fillButtons();
        WordChecker wordChecker = new WordChecker();
        boolean result = wordChecker.check("231", "fic", keyboard);
        assertThat(result, is(true));
    }

    @Test
    public void whenAddIncorrectWordToDictionary() {
        Keyboard keyboard = new Keyboard();
        keyboard.fillButtons();
        WordChecker wordChecker = new WordChecker();
        boolean result = wordChecker.check("231", "asd", keyboard);
        assertThat(result, is(false));
    }
}