package ru.yalymar.list.hascycle;

import org.junit.Test;
import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HasCycleTest {

    @Test
    public void whenHaveCycle(){
        HasCycle<Integer> hasCycle = new HasCycle<>();
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();

        // create nodes
        SimpleLinkedList<Integer>.Node<Integer> first =
                simpleLinkedList.new Node<>(null, 1, null);
        SimpleLinkedList<Integer>.Node<Integer> second =
                simpleLinkedList.new Node<>(null, 2, null);
        SimpleLinkedList<Integer>.Node<Integer> third =
                simpleLinkedList.new Node<>(null, 3, null);
        SimpleLinkedList<Integer>.Node<Integer> fourth =
                simpleLinkedList.new Node<>(null, 4, null);

        // fourth node refers to first one
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;

        boolean result = hasCycle.hasCycle(first);
        assertThat(result, is(true));
    }

    @Test
    public void whenHaveNotCycle(){
        HasCycle<Integer> hasCycle = new HasCycle<>();
        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();

        // create nodes
        SimpleLinkedList<Integer>.Node<Integer> first =
                simpleLinkedList.new Node<>(null, 1, null);
        SimpleLinkedList<Integer>.Node<Integer> second =
                simpleLinkedList.new Node<>(null, 2, null);
        SimpleLinkedList<Integer>.Node<Integer> third =
                simpleLinkedList.new Node<>(null, 3, null);
        SimpleLinkedList<Integer>.Node<Integer> fourth =
                simpleLinkedList.new Node<>(null, 4, null);

        // fourth node refers to first one
        first.next = second;
        second.next = third;
        third.next = fourth;

        boolean result = hasCycle.hasCycle(first);
        assertThat(result, is(false));
    }
}