package ru.yalymar.threads.spacewordcounter;

/**
 * @author slavalymar
 * @since 30.03.2017
 * @version 1
 */
public class Counter {

    /**
     * original string
     */
    private final String string;

    /**
     * array of char of string
     */
    private final char[] chars;

    public Counter(String string) {
        this.string = string;
        this.chars = this.string.toCharArray();
    }

    /** count spaces
     * @return int
     */
    public int countSpaces(){
        System.out.println(String.format("%s%s%s", "Thread ", Thread.currentThread().getName(), " is executing"));

        int counter = 0;
        for(int i = 0; i<this.chars.length; i++){
            if(this.chars[i] == ' ') counter++;
        }
        System.out.println(String.format("%s%s", counter, " spaces."));
        return counter;
    }

    /** count chars
     * @return int
     */
    public int countChars(){
        System.out.println(String.format("%s%s%s", "Thread ", Thread.currentThread().getName(), " is executing"));

        int counter = 0;
        for(int i = 0; i<this.chars.length; i++){
            if(this.chars[i] != ' ') counter++;
        }
        System.out.println(String.format("%s%s", counter, " chars."));
        return counter;
    }


}
