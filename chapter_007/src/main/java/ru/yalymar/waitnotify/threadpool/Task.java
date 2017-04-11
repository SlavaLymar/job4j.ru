package ru.yalymar.waitnotify.threadpool;

public class Task {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void run(Thread t, String name){
        System.out.println(String.format("\"%s\" is completed by \"%s\"!", name, t.getName()));
    }
}
