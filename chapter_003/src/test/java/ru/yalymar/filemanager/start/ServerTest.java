package ru.yalymar.filemanager.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void serverInputPathTest() throws IOException {

        Socket socket = mock(Socket.class);
        Server server = new Server(socket);
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        server.startServer();
        assertThat(out.toString(), is("Welcome to File Manager!"));
    }

    @Test
    public void serverOutputPathTest(){

    }
}