package ru.yalymar.tree.simplesearch;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleTreeTest {

    @Test
    public void whenAddObjectsToContainerThenSearchByKeyShouldGetIt(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(3, "Serega3");
        SimpleTree.Leaf leaf2 = simpleTree.new Leaf(-4, "Serega-4");

        simpleTree.addChild(leaf);
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf2);

        SimpleTree.Leaf result = simpleTree.findByKey(simpleTree.getRoot(), -4);
        assertThat(result.getValue(), is("Serega-4"));
    }
}