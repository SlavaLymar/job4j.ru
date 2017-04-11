package ru.yalymar.waitnotify.threadpool;

public class Task {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void doTask(){
        System.out.println(String.format("Task \"%s\" has completed!", this.name));
    }
}
