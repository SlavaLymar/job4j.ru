package ru.lymar.isp.menu;

import ru.lymar.isp.action.BaseAction;
import java.util.List;

public abstract class Node extends BaseAction{

    private List <Node> nodes;

    /**
     * @param name
     */
    public Node(String name) {
        super(name);
    }


}
