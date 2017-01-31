package ru.yalymar.consolechat;

import org.junit.Test;
import java.io.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

public class ConsoleChatTest {

    @Test
    public void fillListOfAnswersTest() {
        String pathAnswers = "C:\\Java\\junior\\chapter_003\\resources\\Task5\\Task5.txt";
        String result = null;

        try (BufferedReader brAnswers = new BufferedReader(new FileReader(pathAnswers))){

            ConsoleChat consoleChat = new ConsoleChat(brAnswers);
            result = consoleChat.getListOfAnswers().get(4);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result, is("That is what i told."));
    }

    @Test
    public void getAnswerTest() {
        String pathAnswers = "C:\\Java\\junior\\chapter_003\\resources\\Task5\\Task5.txt";
        String result = null;

        try (BufferedReader brAnswers = new BufferedReader(new FileReader(pathAnswers))){

            ConsoleChat consoleChat = new ConsoleChat(brAnswers);
            result = consoleChat.getAnswer();
            System.out.println(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }

}