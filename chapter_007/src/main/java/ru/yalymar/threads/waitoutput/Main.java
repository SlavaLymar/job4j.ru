package ru.yalymar.threads.waitoutput;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String string = "Privet romashki, ty day te dengi... ";
        System.out.println(String.format("%s%s%s",
                "This program will calculate spaces and chars of string: \"", string, "\"."));
        Counter counter =
                new Counter(string);

        final int[] count = new int[2];

        // create new Thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[0] = counter.countSpaces();
            }
        });

        // create new Thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                count[1] = counter.countChars();
            }
        });

        // start threads
        t1.start();
        t2.start();

        /**
         * wait finish threads t1, t2 in 1 second.
         * After that set flag of interrupt = true
         */
        t1.join(1000);
        if(t1.isAlive()) t1.interrupt();

        t2.join(1000);
        if(t2.isAlive()) t1.interrupt();

        t1.join();
        t2.join();

        System.out.println("Finish program in main thread.");
    }
}
