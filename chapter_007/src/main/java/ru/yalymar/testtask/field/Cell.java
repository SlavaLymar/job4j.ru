package ru.yalymar.testtask.field;

import ru.yalymar.testtask.mechanic.Addiction;
import ru.yalymar.testtask.model.Essence;

public abstract class Cell implements Addiction{

    private Essence e;
    private boolean available;

    public Cell(boolean available) {
        this.e = null;
        this.available = available;
    }

    public Essence getE() {
        return this.e;
    }

    public void delete(Essence e) {
        this.e = null;
    }

    public void setE(Essence e) {
        this.e = e;
    }

    public boolean isAvailable() {
        return this.available;
    }

    @Override
    public void add(Essence e) {
        this.e = e;
    }



}
