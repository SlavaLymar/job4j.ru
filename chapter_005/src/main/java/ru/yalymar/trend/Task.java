package ru.yalymar.trend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author slavalymar
 * @since 24.03.2017
 * @version 1
 */
public class Task {

    /**
     * task`s name
     */
    private String name;

    /**
     * state of task
     */
    private State state;

    /**
     * date of create of task
     */
    private Date create;

    /**
     * date of update of task
     */
    private Date upd;

    /**
     * list of operations
     */
    private List<Operation> operations;

    public Task(String name) {
        this.name = name;
        this.state = State.Start;
        this.getCurrentDate();
        this.upd = this.create;
        this.operations = new ArrayList<>();
    }

    private void getCurrentDate() {
        this.create = new Date();
    }

    /**
     * update task
     */
    public void update(){
        this.upd = new Date();
    }

    /** add operation
     * @param operation
     */
    public void addOperation(Operation operation){
        this.operations.add(operation);
    }

    public String getName() {
        return this.name;
    }

    public State getState() {
        return this.state;
    }

    public Date getCreate() {
        return this.create;
    }

    public Date getUpd() {
        return this.upd;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }
}
