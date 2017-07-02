package ru.yalymar.testtask.dfs;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int value;
    private boolean flag;
    private Node childMin;
    private Node childMax;
    private Node parent;

    public Node(int value, Node parent) {
        this.value = value;
        this.flag = false;
        this.childMin = null;
        this.childMax = null;
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getChildMin() {
        return childMin;
    }

    public void setChildMin(Node childMin) {
        this.childMin = childMin;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Node getChildMax() {
        return childMax;
    }

    public void setChildMax(Node childMax) {
        this.childMax = childMax;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren(){
        return new ArrayList<Node>(){{
            if(childMin != null){
                add(childMin);
            }
            if(childMax != null){
                add(childMax);
            }
        }};
    }
}
