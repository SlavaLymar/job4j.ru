package ru.yalymar.testtask.dfs;

import java.util.List;

public class Tree {

    private Node root;

    public Tree() {
        this.root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void add(int node){
        if(this.root == null){
            this.root = new Node(node, null);
        }
        else {
            this.searchInsertPlace(node, this.root);
        }
    }

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

    public void setMirrorTree(Node start){
        start = this.root;
        if(!start.isFlag()){
            start.setFlag(true);
            List<Node> nodes = start.getChildren();
            for(int i = 0; i < nodes.size(); i++){
                if (nodes.get(i).getChildren().size() > 0
                        && !nodes.get(i).isFlag()) {
                    this.setMirrorTree(nodes.get(i));
                }
                if(i + 1 == nodes.size()){
                    Node tmp = nodes.get(i - 1);
                    nodes.set(i - 1, nodes.get(i));
                    nodes.set(i, tmp);
                }
            }
        }
    }


}
