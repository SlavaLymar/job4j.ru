package ru.yalymar.map.directory;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class DictionaryTest {

    @Test
    public void whenInsertValueThenShouldGetTrueByHasNext(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.insert("Vasya", 2);
        Dictionary.DictionaryIterator dictionaryIterator =
                dictionary.new DictionaryIterator();

        assertThat(dictionaryIterator.hasNext(), is(true));
    }

    @Test
    public void whenInsertValuesThenShouldGetValueByKey(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.insert("Vasya", 2);
        dictionary.insert("Grisha", 3);
        dictionary.insert("Petya", 1);

        assertThat(dictionary.get("Grisha"), is(3));
    }

    @Test
    public void whenInsertValuesThenShouldDeleteThenWhenTryGetItWillBeNull(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.insert("Vasya", 2);
        dictionary.insert("Grisha", 3);
        dictionary.insert("Petya", 1);
        dictionary.delete("Grisha");

        assertNull(dictionary.get("Grisha"));
    }

    @Test
    public void whenMakeCollisionShouldBeNewValue(){
        Dictionary<String, Integer> dictionary = new Dictionary<>();

        dictionary.insert("Vasya", 2);
        dictionary.insert("Grisha", 3);
        dictionary.insert("Vasya", 1);

        assertThat(dictionary.get("Vasya"), is(1));
    }

}