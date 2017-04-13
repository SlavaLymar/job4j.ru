package ru.yalymar.nonblock.nonblockcache;

/**
 * @author slavalymar
 * @since 14.04.2017
 * @version 1
 */
public class Task {

    /**
     * task`s name
     */
    private final String name;

    /**
     * task`s value
     */
    private int value;

    /**
     * task`s version
     */
    private int version;

    public Task(String name, int value) {
        this.name = name;
        this.value = value;
        this.version = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
