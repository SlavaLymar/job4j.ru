package ru.yalymar.filemanager.filemanager;

import org.junit.Test;
import ru.yalymar.filemanager.exceptions.DontExistException;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

public class FileManagerTest {

    FileManager fileManager = new FileManager();

    @Test
    public void changeDirectoryTest() throws DontExistException {
        String file = this.fileManager.changeDirectory("D:/dstr/job4j.ru/chapter_003/cd target");
        assertThat(file, is("D:/dstr/job4j.ru/chapter_003/target/"));
    }

    @Test
    public void backTest() throws DontExistException {
        String file = this.fileManager.back("D:/dstr/job4j.ru/chapter_003/cd..");
        assertThat(file, is("D:/dstr/job4j.ru/"));
    }

    @Test
    public void downloadTest() throws FileNotFoundException {
            String file = this.fileManager.download("D:/dstr/job4j.ru/chapter_003/download pom.xml");
            assertThat(file, is("D:/dstr/job4j.ru/chapter_003/pom.xml"));
    }

    @Test
    public void uploadTest() {
        String[] files = this.fileManager.upload
                ("D:/dstr/job4j.ru/chapter_003/src/main/resources/upload D:/dstr/job4j.ru/chapter_003/pom.xml");
        String[] expected = new String[2];
        expected[0] = "D:/dstr/job4j.ru/chapter_003/src/main/resources/";
        expected[1] = "D:/dstr/job4j.ru/chapter_003/pom.xml";
        assertArrayEquals(files, expected);
    }

}