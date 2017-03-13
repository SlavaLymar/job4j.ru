package ru.yalymar.set.quickaddtoset;

import org.junit.Test;
import ru.yalymar.set.arraylistsimpleset.SimpleHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class QuickSimpleHashSetTest {

    @Test
    public void whenAddDuplicateObjectsThenGetNull(){
        String[] array = new String[10];
        SimpleHashSet<String> set = new SimpleHashSet<>(array);

        set.add("Vasya");
        set.add("java");
        set.add("core");
        set.add("89");
        set.add("Yaroslav");
        set.add("core");

        assertNull(set.getContainer()[5]);
        assertThat();
    }

}