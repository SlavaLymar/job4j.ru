package ru.yalymar.testtask.bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 02.07.2017
 * @version 1
 */
public class Node {

    private int value;
    private Node childMin;
    private Node childMax;
    private Node parent;

    public Node(int value, Node parent) {
        this.value = value;
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

    /** get node`s children
     * @return List
     */
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

    /**
     * reflect nodes into vertex
     */
    public void reflectNodes(){
        Node tmp = this.childMin;
        this.childMin = this.childMax;
        this.childMax = tmp;
    }
}
