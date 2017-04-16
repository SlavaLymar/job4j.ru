package ru.yalymar.testtask.field;

import ru.yalymar.testtask.mechanic.Addiction;
import ru.yalymar.testtask.model.Essence;

/**
 * @author slavalymar
 * @since 16.04.2017
 * @version 1
 */
public abstract class Cell implements Addiction{

    /**
     * essence`s instance
     */
    private Essence e;

    /**
     * flag of availability
     */
    private boolean available;

    public Cell(boolean available) {
        this.e = null;
        this.available = available;
    }

    /** add essence
     * @param e
     */
    @Override
    public synchronized void add(Essence e) {
        this.e = e;
    }

    /** get essence
     * @return Essence
     */
    public Essence getE() {
        return this.e;
    }

    /**
     * delete essence
     */
    public synchronized void delete() {
        this.e = null;
    }

    /** set essence
     * @param e
     */
    public synchronized void setE(Essence e) {
        this.e = e;
    }

    public boolean isAvailable() {
        return this.available;
    }





}
