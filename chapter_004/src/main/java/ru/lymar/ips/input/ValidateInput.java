package ru.lymar.ips.input;

import java.util.List;

public class ValidateInput extends ConsoleInput{

    @Override
    public String ask(String question, List<String> list){
        String value = "";
        while(value.equals("")) {
            value = super.ask(question, list);
        }
        return value;
    }
}
