package ru.lymar.ips.input;

import java.util.List;

public interface AskableMenu {

    String ask(String question, List<String> list);
}
