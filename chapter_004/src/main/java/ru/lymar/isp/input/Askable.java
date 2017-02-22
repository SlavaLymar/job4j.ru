package ru.lymar.isp.input;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public interface Askable {

    /**
     * @param question
     * @return String
     */
    String ask(String question);
}
