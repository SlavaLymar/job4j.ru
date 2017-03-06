package ru.yalymar.generic.simplearray;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class SimpleArrayTest {

    String[] arr;
    SimpleArray<String> simpleArray;

    public void init(){
        this.arr = new String[10];
        this.simpleArray = new SimpleArray<>(arr);
    }

    @Test
    public void whenAddValueShouldGetValue() {
        this.init();
        this.simpleArray.add("Privet");
        assertThat(this.simpleArray.getArr()[0], is("Privet"));
    }

    @Test
    public void whenUpdateValueShouldGetNewValue() {
        this.init();
        this.whenAddValueShouldGetValue();
        this.simpleArray.update(0, "Hello");
        assertThat(this.simpleArray.getArr()[0], is("Hello"));
    }

    @Test
    public void whenGetValue() {
        this.init();
        this.whenAddValueShouldGetValue();
        String result = this.simpleArray.get(0);
        assertThat(result, is("Privet"));
    }

    @Test
    public void whenDeleteValueShouldGetNull() {
        this.init();
        this.whenAddValueShouldGetValue();
        this.simpleArray.delete(0);
        assertNull(this.simpleArray.getArr()[0]);
    }

}