package ru.yalymar.filemanager.filemanager;

import org.junit.Test;
import ru.yalymar.filemanager.exceptions.DontExistException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;

public class FileManagerTest {

    FileManager fileManager = new FileManager();

    @Test
    public void getListTest() {
        String [] dir = this.fileManager.getList("C:/Java/junior/examples/dir");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String str = "";
        for (String f : dir) {
            String time = sdf.format(new Date(new File(f).lastModified()));

            str = String.format("%s%s%7s%10s%20s%s", str, time, (new File(f).isDirectory() ? "<DIR>" : "  "),
                    new File(f).length(), new File(f).getName(), System.getProperty("line.separator"));
        }
        assertThat(dir[0], is("examples.iml"));
    }

    @Test
    public void changeDirectoryTest() {
        try {
            String file = this.fileManager.changeDirectory("C:/Java/junior/examples/cd target");
            assertThat(file, is("C:/Java/junior/examples/target"));
        } catch (DontExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void backTest() {
        try {
            String file = this.fileManager.back("C:/Java/junior/examples/target/cd..");
            assertThat(file, is("C:/Java/junior/examples/"));
        } catch (DontExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadTest() {
        try {
            String file = this.fileManager.download("C:/Java/junior/examples/download pom.xml");
            assertThat(file, is("C:/Java/junior/examples/pom.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadTest() {
        String[] files = this.fileManager.upload
                ("C:/Java/junior/examples/upload C:/Java/junior/examples/pom.xml");
        String[] expected = new String[2];
        expected[0] = new String("C:/Java/junior/examples/");
        expected[1] = new String("C:/Java/junior/examples/pom.xml");
        assertArrayEquals(files, expected);
    }

}