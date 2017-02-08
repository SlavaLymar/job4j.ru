package ru.yalymar.filemanager.start;


import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    @Test
    public void downloadTest() throws IOException {
        Socket socket = mock(Socket.class);
        Client client = new Client(socket);
        ByteArrayInputStream in = new ByteArrayInputStream("download pom.xml".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        //byte[] b = new File("C:/Java/junior/examples/pom.xml").toString().getBytes();
        //assertThat(in.toString(), is(b));
    }
}