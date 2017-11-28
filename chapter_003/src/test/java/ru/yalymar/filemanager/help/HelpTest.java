package ru.yalymar.filemanager.help;

import org.junit.Test;
import ru.yalymar.filemanager.exceptions.DontExistException;
import ru.yalymar.filemanager.filemanager.FileManager;
import ru.yalymar.filemanager.input.ClientInput;
import ru.yalymar.filemanager.output.ClientOutput;
import ru.yalymar.filemanager.start.Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelpTest {

    private Socket socket;
    private Server server;
    private FileManager fileManager;
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;
    private Help help;

    private void startHelpTest() throws IOException, DontExistException {
        this.socket = mock(Socket.class);
        this.server = new Server(socket);
        this.fileManager = new FileManager();
        this.in = new ByteArrayInputStream(""
                .getBytes());
        this.out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        this.help = new Help(new ClientInput(socket), new ClientOutput(socket),
                fileManager, "D:/dstr/job4j.ru/chapter_003/src/main/");
        help.fillHelp();
    }

    @Test
    public void backDirTest() throws DontExistException, IOException{
        startHelpTest();
        help.select("cd..");
        assertThat(out.toString(), is(String.format("%s%s", "D:/dstr/job4j.ru/chapter_003/src/",
                System.getProperty("line.separator"))));
    }

    @Test
    public void changeDirTest() throws DontExistException, IOException{
        startHelpTest();
        help.select("cd resources");
        assertThat(out.toString(), is(String.format("%s%s", "D:/dstr/job4j.ru/chapter_003/src/main/resources/",
                System.getProperty("line.separator"))));
    }
}