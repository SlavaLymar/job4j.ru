package ru.yalymar.set.quickaddtoset;

import org.junit.Test;
import ru.yalymar.set.arraylistsimpleset.SimpleHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class QuickSimpleHashSetTest {

    @Test
    public void whenAddDuplicateObjectsToQuickSimpleHashSetThenGetTime(){
        String[] array = new String[10];
        QuickSimpleHashSet<String> set = new QuickSimpleHashSet<>(array);

        long startSet = System.nanoTime();
        // add to QuickSet
        set.add("Vasya");
        set.add("java");
        set.add("core");
        set.add("89");
        set.add("Yaroslav");
        set.add("core");
        long finishSet = System.nanoTime();
        System.out.println("Time QuickSet "+(finishSet-startSet)+" ns");

        int counter = 0;
        for(String s: set.getContainer()){
            if(s != null) counter++;
        }
        assertNull(set.getContainer()[5]);
        assertThat(counter, is(5));
    }

    @Test
    public void whenAddDuplicateObjectsToSimpleHashSetThenGetTime(){
        String[] array = new String[10];
        SimpleHashSet<String> set1 = new SimpleHashSet<>(array);

        long startSet1 = System.nanoTime();
        // add to Set
        set1.add("Vasya");
        set1.add("java");
        set1.add("core");
        set1.add("89");
        set1.add("Yaroslav");
        set1.add("core");
        long finishSet1 = System.nanoTime();
        System.out.println("Time Set "+(finishSet1-startSet1)+" ns");

        int counter = 0;
        for(String s: set1.getContainer()){
            if(s != null) counter++;
        }
        assertNull(set1.getContainer()[5]);
        assertThat(counter, is(5));
    }
}