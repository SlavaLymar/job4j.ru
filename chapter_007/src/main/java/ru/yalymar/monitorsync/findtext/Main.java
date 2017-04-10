package ru.yalymar.monitorsync.findtext;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String text = args[0];
        FindText ft = new FindText(text);
        Thread t = ft.new SearchFile();
        t.start();
        t.join();

        System.out.println(String.format("The text \"%s\" contains in \"%s\"",
                text, ft.getFile().getAbsolutePath()));
    }
}
