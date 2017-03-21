package ru.yalymar.tree.binarysearch;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleTreeTest {

    @Test
    public void whenSimpleSearchLeafShouldGetIt(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(3, "Serega3");
        SimpleTree.Leaf leaf2 = simpleTree.new Leaf(-4, "Serega-4");
        SimpleTree.Leaf leaf3 = simpleTree.new Leaf(2, "Serega2");
        SimpleTree.Leaf leaf4 = simpleTree.new Leaf(10, "Serega10");
        SimpleTree.Leaf leaf5 = simpleTree.new Leaf(4, "Serega4");
        SimpleTree.Leaf leaf6 = simpleTree.new Leaf(-40, "Serega-40");
        SimpleTree.Leaf leaf7 = simpleTree.new Leaf(6, "Serega6");

        simpleTree.addChild(leaf);
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf2);
        simpleTree.addChild(leaf3);
        simpleTree.addChild(leaf4);
        simpleTree.addChild(leaf5);
        simpleTree.addChild(leaf6);
        simpleTree.addChild(leaf7);

        long start = System.nanoTime();
        SimpleTree.Leaf result = simpleTree.simpleSearch(6);
        long finish = System.nanoTime();
        System.out.println("simplesearch "+(finish-start)+" ns");
        assertThat(result.getValue(), is("Serega6"));
    }

    @Test
    public void whenSearchLeafShouldGetIt(){
        SimpleTree<Integer, String> simpleTree = new SimpleTree<>();
        SimpleTree.Leaf leaf = simpleTree.new Leaf(1, "Serega");
        SimpleTree.Leaf leaf1 = simpleTree.new Leaf(3, "Serega3");
        SimpleTree.Leaf leaf2 = simpleTree.new Leaf(-4, "Serega-4");
        SimpleTree.Leaf leaf3 = simpleTree.new Leaf(2, "Serega2");
        SimpleTree.Leaf leaf4 = simpleTree.new Leaf(10, "Serega10");
        SimpleTree.Leaf leaf5 = simpleTree.new Leaf(4, "Serega4");
        SimpleTree.Leaf leaf6 = simpleTree.new Leaf(-40, "Serega-40");
        SimpleTree.Leaf leaf7 = simpleTree.new Leaf(6, "Serega6");

        simpleTree.addChild(leaf);
        simpleTree.addChild(leaf1);
        simpleTree.addChild(leaf2);
        simpleTree.addChild(leaf3);
        simpleTree.addChild(leaf4);
        simpleTree.addChild(leaf5);
        simpleTree.addChild(leaf6);
        simpleTree.addChild(leaf7);

        long start = System.nanoTime();
        SimpleTree.Leaf result = simpleTree.findByKey(simpleTree.getRoot(), 6);
        long finish = System.nanoTime();
        System.out.println("binarysearch "+(finish-start)+" ns");
        assertThat(result.getValue(), is("Serega6"));
    }
}