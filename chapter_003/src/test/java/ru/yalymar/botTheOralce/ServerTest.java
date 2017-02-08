package ru.yalymar.botTheOralce;

import org.junit.Test;
import java.io.*;
import java.net.Socket;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void inputTest() throws IOException {
        Socket socket = mock(Socket.class);
        Server server = new Server(socket);
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        server.startServer();
        assertThat(out.toString(),
                is(String.format("%s%s", "Oracle: Bye bye. See you later.",
                        System.getProperty("line.separator"))));
    }


}