package ru.lymar.tdd;

import ru.lymar.tdd.exceptions.ExtraKeysException;
import ru.lymar.tdd.exceptions.NotAvailableKeysException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author slavalymar
 * @sence 26.02.2017
 * @version 1
 */
public class SimpleGenerator implements Template{

    private Pattern PATTERN = Pattern.compile("[$][{].+?}");

    /** generate phrase
     * @param text
     * @param maps
     * @return String
     * @throws NotAvailableKeysException
     * @throws ExtraKeysException
     */
    @Override
    public String generate(String text, Map<String, String> maps)
            throws NotAvailableKeysException, ExtraKeysException{

        //matcher block
        Matcher matcher = PATTERN.matcher(text);

        //if keys missing throw exception
        if (!matcher.find()) throw new NotAvailableKeysException("There are not keys!");

        // reset matcher
        matcher.reset();

        //change keys to values
        while(matcher.find()){
            if(maps.containsKey(matcher.group(0))) {
                text = text.replace(matcher.group(0), maps.get(matcher.group(0)));
            }

            // if key missing in maps throw exception
            else throw new ExtraKeysException("This key is not exist!");
        }
        return text;
    }


}
