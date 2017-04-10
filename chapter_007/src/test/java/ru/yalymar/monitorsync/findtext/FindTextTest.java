package ru.yalymar.monitorsync.findtext;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FindTextTest {

    @Test
    public void whenSearchTextShouldGetPath() throws InterruptedException {
        FindText ft = new FindText("mama myla ramu", "C:/Java/job4j.ru/chapter_007");
        Thread t = ft.new SearchFile();
        t.start();
        t.join();

        for(FindText.SearchText st: ft.getList()){
            if(st != null) st.join();
        }

        assertThat(ft.getFile().toString(), is("C:\\Java\\job4j.ru\\chapter_007\\resources\\searchtext.txt"));
    }
}