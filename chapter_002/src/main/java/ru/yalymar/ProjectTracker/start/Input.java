package ru.yalymar.start;

/**
 * @author slavalymar
 * @since 13.01.2017
 * @version 1
 */
public interface Input {

    String ask(String question);

    int ask(String question, int[] arr);

    int getNumber(String question);


}
