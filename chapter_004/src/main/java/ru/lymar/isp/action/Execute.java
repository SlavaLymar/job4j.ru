package ru.lymar.isp.action;

import ru.lymar.isp.input.Input;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public interface Execute {

    /**
     * @return String
     */
    String key();

    /**
     * @param input
     */
    void execute(Input input);
}
