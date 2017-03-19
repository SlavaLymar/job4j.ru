package ru.yalymar.tree.isbalance;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleTreeTest {

    @Test
    public void whenAddObjectsThenCheckedIsBalancedShouldGetFalse(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(3, "Serega3");
        SimpleTree.Leaf leaf2 = simpleTree.new Leaf(-4, "Serega-4");
        SimpleTree.Leaf leaf3 = simpleTree.new Leaf(2, "Serega2");

        simpleTree.addChild(leaf);
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf2);
        simpleTree.addChild(leaf3);

        boolean result = simpleTree.isBalanced();

        assertThat(result, is(false));
    }

    @Test
    public void whenAddObjectsThenCheckedIsBalancedShouldGetTrue(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(3, "Serega3");
        SimpleTree.Leaf leaf2 = simpleTree.new Leaf(-4, "Serega-4");

        simpleTree.addChild(leaf);
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf2);

        boolean result = simpleTree.isBalanced();

        assertThat(result, is(true));
    }
}