package ru.yalymar.waitnotify.lock;

/**
 * @author slavalymar
 * @since 13.04.2017
 * @version 1
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                this.setName("Thread-1");
                if(myLock.tryLock()){
                    this.execute();
                    myLock.unlock();
                }
            }

            public void execute(){
                System.out.println(String.format("%s is executing!", this.getName()));
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                this.setName("Thread-2");
                if(myLock.tryLock()){
                    this.execute();
                    myLock.unlock();
                }
            }

            public void execute(){
                System.out.println(String.format("%s is executing!", this.getName()));
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                this.setName("Thread-3");
                try {
                    myLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.execute();
                myLock.unlock();
            }

            public void execute(){
                System.out.println(String.format("%s is executing!", this.getName()));
            }
        };

        // start threads
        t1.start();
        t2.start();
        t3.start();

        // wait threads
        t1.join();
        t2.join();
        t3.join();

        System.out.println("Main Thread is completed!");
    }
}
