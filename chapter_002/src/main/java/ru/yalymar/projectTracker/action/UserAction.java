package ru.yalymar.projectTracker.action;

import ru.yalymar.projectTracker.start.Input;
import ru.yalymar.projectTracker.tracker.Tracker;

public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String print();
}
