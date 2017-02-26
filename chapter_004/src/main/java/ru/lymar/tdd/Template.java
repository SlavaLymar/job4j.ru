package ru.lymar.tdd;

import ru.lymar.tdd.exceptions.ExtraKeysException;
import ru.lymar.tdd.exceptions.NotAvailableKeysException;
import java.util.Map;

/**
 * @author slavalymar
 * @sence 26.02.2017
 * @version 1
 */
public interface Template {

    /** generate phrase
     * @param text
     * @param maps
     * @return String
     * @throws NotAvailableKeysException
     * @throws ExtraKeysException
     */
    String generate(String text, Map <String, String> maps)
            throws NotAvailableKeysException, ExtraKeysException;
}
