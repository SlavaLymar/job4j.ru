package ru.lymar.ips.action;

import ru.lymar.ips.input.Input;

public interface Execute {

    String key();

    void execute(Input input);
}
