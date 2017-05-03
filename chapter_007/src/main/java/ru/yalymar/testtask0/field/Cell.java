package ru.yalymar.testtask0.field;

import ru.yalymar.testtask0.mechanic.Addiction;
import ru.yalymar.testtask0.model.Essence;

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
    public synchronized Essence getE() {
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

    public synchronized boolean isAvailable() {
        return this.available;
    }

}
