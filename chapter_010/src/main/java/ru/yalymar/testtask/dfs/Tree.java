package ru.yalymar.testtask.dfs;

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

    /** implementation of depth-first search (DFS)
     * for mirroring of the tree
     * @param start
     */
    public void setMirrorTree(Node start){
        if(!start.isFlag()){
            start.setFlag(true);
            List<Node> nodes = start.getChildren();
            for(int i = 0; i < nodes.size(); i++){
                if (nodes.get(i).getChildren().size() > 0
                        && !nodes.get(i).isFlag()) {
                    this.setMirrorTree(nodes.get(i));
                }
                if(i + 1 == nodes.size()){
                    Node tmp = start.getChildMin();
                    start.setChildMin(start.getChildMax());
                    start.setChildMax(tmp);
                }
            }
            this.root = start;
        }
    }


}
