package ru.lymar.isp.input;

import java.util.List;

/**
 * @author slavalymar
 * @since 22.02.2017
 * @version 1
 */
public class ValidateInput extends ConsoleInput{

    /**
     * @param question
     * @param list
     * @return String
     */
    @Override
    public String ask(String question, List<String> list){
        String value = "";
        while(value.equals("")) {
            value = super.ask(question, list);
        }
        return value;
    }
}
