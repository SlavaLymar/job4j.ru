package ru.yalymar.set.linkedlistsimpleset;

import org.junit.Test;
import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleLinkedSetTest {

    @Test
    public void whenAddDuplicateObjectsThenItDoesntGet(){
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        SimpleLinkedSet set = new SimpleLinkedSet(list);

        set.add(1);
        set.add(-10);
        set.add(1);

        assertThat(set.getContainer().size(), is(2));
    }
}