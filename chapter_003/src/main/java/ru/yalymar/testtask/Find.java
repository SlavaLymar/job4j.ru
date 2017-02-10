package ru.yalymar.testtask;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

    private boolean isValidate = false;

    /** check keys
     * @param args
     * @throws NotValidateException
     */
    public void validate(String[] args) throws NotValidateException {

        // [-d, C:/Java, -n, 1.txt, -regex,f,mask, -o, log.txt]
        String regex = ".-d,\\s.+,\\s-n,.+,\\s-.+,\\s-o,\\s.+.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Arrays.deepToString(args));
        if(matcher.find()){
            this.isValidate = true;
        }
        else throw new NotValidateException("Incorrect keys");
    }

    public static void main(String[] args) {

        try {
            Find find = new Find();
            find.validate(args);
            if(find.getIsValidate()){
                Search search = new Search();
                search.fillKeys(args);
                search.fillParam();

            }
        }
        catch(NotValidateException e){
            e.printStackTrace();
        }
    }

    public boolean getIsValidate() {
        return isValidate;
    }

}
