package ru.yalymar.trend;

import java.util.Calendar;

/**
 * @author slavalymar
 * @since 24.03.2017
 * @version 1
 */
public class Operation {

    /**
     * operation`s name
     */
    private String name;

    /**
     * state of operation
     */
    private State state;

    /**
     * index of states
     */
    private int index = 0;

    /**
     * finish operation
     */
    private Calendar deadLine;

    public Operation(String name, Calendar deadLine) {
        this.name = name;
        this.state = State.values()[index];
        this.deadLine = deadLine;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public Calendar getDeadLine() {
        return deadLine;
    }
}
