package ru.lymar.isp.input;

import java.util.List;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public interface AskableMenu {

    /**
     * @param question
     * @param list
     * @return String
     */
    String ask(String question, List<String> list);
}
