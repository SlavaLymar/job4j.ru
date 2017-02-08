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
    public void inputTestExit() throws IOException {
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

    @Test
    public void inputTestHello() throws IOException {
        Socket socket = mock(Socket.class);
        Server server = new Server(socket);
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("%s%s%s", "hello", System.getProperty("line.separator"),
                        "exit").getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        server.startServer();
        assertThat(out.toString(),
                is(String.format("%s%s%s%s", "Oracle: Hello, dear friend, I'm a oracle.",
                        System.getProperty("line.separator"), "Oracle: Bye bye. See you later.",
                        System.getProperty("line.separator"))));
    }


}