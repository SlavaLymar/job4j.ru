package ru.lymar.testtask;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class SettingTest {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    public void whenAskSize(){
        systemInMock.provideLines("4");
        Setting setting = new Setting();
        setting.askSize();
        assertThat(setting.getSize(), is(4));
    }

    @Test
    public void whenAskFirtStep(){
        systemInMock.provideLines("2");
        Setting setting = new Setting();
        setting.askFirstStep();
        assertThat(setting.getPlayers()[0].getName(), is("AI"));
    }

}