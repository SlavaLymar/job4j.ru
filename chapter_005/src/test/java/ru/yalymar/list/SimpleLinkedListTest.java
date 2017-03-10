package ru.yalymar.list;

import org.junit.Test;
import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;
import java.util.NoSuchElementException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleLinkedListTest {

    @Test
    public void whenAddValueToLinkedListThenGetIt(){
        SimpleLinkedList simpleLinkedList = new SimpleLinkedList();
        simpleLinkedList.add(new Integer(1));

        assertThat(simpleLinkedList.get(0), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddValueThenGetItWithNextAndGenerateException(){
        SimpleLinkedList simpleLinkedList = new SimpleLinkedList();
        simpleLinkedList.add(new Integer(1));
        simpleLinkedList.add(new Integer(2));
        simpleLinkedList.add(new Integer(3));
        simpleLinkedList.add(new Integer(4));

        assertThat(simpleLinkedList.getIterator(1).next(), is(2));
        assertThat(simpleLinkedList.getIterator(3).next(), is(4));

        //generate NoSuchElementException
        assertThat(simpleLinkedList.getIterator(4).next(), is(4));
    }
}