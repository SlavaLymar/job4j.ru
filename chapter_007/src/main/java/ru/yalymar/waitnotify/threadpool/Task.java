package ru.yalymar.waitnotify.threadpool;

public class Task {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void run(Thread t){
        System.out.println(String.format("Task1 is completed by \"%s\"!", t.getName()));
    }
}
