package ru.yalymar.testtask.bfs;

import java.util.List;

/**
 * @author slavalymar
 * @since 02.07.2017
 * @version 1
 */
public class Tree {

    private Node root;

    public Tree() {
        this.root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    /** add node into tree
     * @param node
     */
    public void add(int node){
        if(this.root == null){
            this.root = new Node(node, null);
        }
        else {
            this.searchInsertPlace(node, this.root);
        }
    }

    /** search place to insert a node
     * @param value
     * @param node
     */
    private void searchInsertPlace(int value, Node node) {
        if(value <= node.getValue()){
            if(node.getChildMin() == null){
                node.setChildMin(new Node(value, node));
            }
            else {
                this.searchInsertPlace(value, node.getChildMin());
            }
        }
        else if(value > node.getValue()){
            if(node.getChildMax() == null){
                node.setChildMax(new Node(value, node));
            }
            else {
                this.searchInsertPlace(value, node.getChildMax());
            }
        }
    }


}
