package ru.yalymar.action;

import ru.yalymar.start.Input;
import ru.yalymar.tracker.Tracker;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String print();
}
