package ru.yalymar.filemanager.start;

import org.junit.Test;

import java.net.Socket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ClientTest {

    @Test
    public void getFile() {
        Socket socket = mock(Socket.class);
        Client client = new Client(socket);
        String result = client.getPath();
        assertThat(result, is
                ("D:\\dstr\\job4j.ru\\chapter_003\\src\\main\\resources"));
    }

}