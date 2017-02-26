package ru.lymar.tdd;

import org.junit.Test;
import ru.lymar.tdd.exceptions.ExtraKeysException;
import ru.lymar.tdd.exceptions.NotAvailableKeysException;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleGeneratorTest {

    @Test
    public void whenSendKeysThenCountGroup() throws NotAvailableKeysException, ExtraKeysException {
        SimpleGenerator sg = new SimpleGenerator();
        String text = "Hello ${name1}, how are ${pronoun1}?";
        Map maps = new HashMap<String, String>();
        maps.put("${name1}", "Slava");
        maps.put("${pronoun1}", "you");
        String result = sg.generate(text, maps);
        String expected = "Hello Slava, how are you?";
        assertThat(result, is(expected));
    }
}