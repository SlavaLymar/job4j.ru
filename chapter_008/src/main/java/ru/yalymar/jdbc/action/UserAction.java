package ru.yalymar.jdbc.action;

import ru.yalymar.jdbc.start.Input;
import ru.yalymar.jdbc.tracker.Tracker;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String print();
}
