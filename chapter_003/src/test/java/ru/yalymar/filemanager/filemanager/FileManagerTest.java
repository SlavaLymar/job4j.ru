package ru.yalymar.filemanager.filemanager;

import org.junit.Test;
import ru.yalymar.filemanager.exceptions.DontExistException;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileManagerTest {

    FileManager fileManager = new FileManager();

    @Test
    public void getListTest() {
        File[] dir = this.fileManager.getList("C:/Java/junior/examples/dir");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        for(File f : dir){
            String time = sdf.format(new Date(f.lastModified()));

            String str =
                    String.format("%s%7s%10s%20s", time, (f.isDirectory()? "<DIR>" : "  "),
                            f.length(), f.getName());
            System.out.println(str);
        }
        assertThat(dir[0].getName(), is("examples.iml"));
    }

    @Test
    public void changeDirectoryTest() {
        try {
            File file = this.fileManager.changeDirectory("C:/Java/junior/examples/cd target");
            assertThat(file, is(new File("C:/Java/junior/examples/target")));
        } catch (DontExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void backTest() {
        try {
            File file = this.fileManager.back("C:/Java/junior/examples/target/cd..");
            assertThat(file, is(new File("C:/Java/junior/examples/")));
        } catch (DontExistException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void downloadTest() {
        try {
            File file = this.fileManager.download("C:/Java/junior/examples/download ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadTest() {

    }

}