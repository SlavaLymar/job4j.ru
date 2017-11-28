package ru.yalymar.monitorsync.findtext;

import org.junit.Test;

public class FindTextTest {

    @Test
    public void whenSearchTextShouldGetPath() throws InterruptedException {
        FindText ft = new FindText("mama myla ramu", "D:/dstr/job4j.ru/chapter_007");
        Thread t = ft.new SearchFile();
        t.start();
        t.join();

        for(FindText.SearchText st: ft.getList()){
            if(st != null) st.join();
        }

//        assertThat(ft.getFile().toString(), is("D:\\dstr\\job4j.ru\\chapter_007\\src\\main\\resources\\searchtext.txt"));
    }
}