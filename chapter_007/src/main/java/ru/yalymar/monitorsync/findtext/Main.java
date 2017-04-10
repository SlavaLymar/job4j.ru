package ru.yalymar.monitorsync.findtext;

/**
 * @author slavalymar
 * @since 10.04.2017
 * @version 1
 */
public class Main {

    /** main method
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        String text = args[0];
        FindText ft = new FindText(text);
        Thread t = ft.new SearchFile();
        t.start();

        do {
            Thread.sleep(1);
        }
        while(Thread.activeCount() > 2);

        System.out.println(String.format("The text \"%s\" contains in \"%s\"",
                text, ft.getFile().getAbsolutePath()));
    }
}
