package ru.yalymar.testtask;

public class Main {

    private Field field;

    public void init() throws InterruptedException {
        this.field = new Field(5);
        Thread.currentThread().join();
        this.gameLoop();
    }

    private void gameLoop(){
        new Thread(){
            @Override
            public void run() {

            }
        }.start();

        new Thread(){
            @Override
            public void run() {

            }
        }.start();

        do{



        }
        while(this.field.isAliveBomberman() && this.field.win());
    }

    public static void main(String[] args) throws InterruptedException {
        new Main().init();
    }
}
