package ru.yalymar.tree;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class SimpleTreeTest {

    @Test
    public void whenAddValuesToTreeShouldGetHisChildren(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(3, "Serega3");
        SimpleTree.Leaf leaf2 = simpleTree.new Leaf(-4, "Serega-4");
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf);
        simpleTree.addChild(leaf2);

        List<SimpleTree.Leaf> result = new ArrayList();
        result.addAll(simpleTree.getChildren(3));

        assertThat(result.get(0).getValue(), is(leaf.getValue()));
    }

    @Test
    public void whenAddValueToTreeShouldGetHisChildren(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        simpleTree.addChild(leaf);

        List<SimpleTree.Leaf> result = new ArrayList<>();
        result.addAll(simpleTree.getChildren(1));

        assertThat(result.size(), is(0));
    }

    @Test
    public void whenAddValueToTreeShouldTestIterator(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        simpleTree.addChild(leaf);

        List result = new ArrayList();
        int index = 0;
        while(simpleTree.getRoot().hasNext()){
            result.add(index++, simpleTree.getRoot().next());
        }
        assertThat(result.size(), is(1));
    }

    @Test
    public void whenAddValuesWithEqualsKeysToTreeShouldGetListSize(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(1, "Serega3");
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf);

        List result = new ArrayList();
        int index = 0;
        while(simpleTree.getRoot().hasNext()){
            result.add(index++, simpleTree.getRoot().next());
        }

        assertThat(result.size(), is(2));
    }
}