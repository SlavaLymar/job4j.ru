package ru.yalymar.testtask.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slavalymar
 * @since 02.07.2017
 * @version 1
 */
public class PrintTree {

    private Tree tree;
    private List<PrintNode> nodes;

    public PrintTree(Tree tree) {
        this.tree = tree;
        this.nodes = new ArrayList<>();
    }

    public List<PrintNode> getNodes() {
        return this.nodes;
    }

    /**
     * wrapper for printing
     */
    public class PrintNode{

        public Node node;

        /**
         * value that describe level of depth
         */
        public int level;

        public PrintNode(Node node, int level){
            this.node = node;
            this.level = level;
        }

        @Override
        public String toString() {
            return String.valueOf(node.getValue());
        }
    }

    /**
     * print tree
     */
    public void print(){
        int width = ((this.nodes.get(this.nodes.size()-1).level + 1) * 2) + 1;
        int length = this.nodes.get(this.nodes.size()-1).level + 1;
        int spaces = width/2;
        for (int i = 0; i < length; i++){
            int finalI = i;
            int finalSpaces = spaces;
            this.nodes.forEach((n) ->{
                if(n.level == finalI) {
                    for(int l = 0; l < finalSpaces; l++) {
                        System.out.print(String.format("%s", " "));
                    }
                    System.out.print(n);
                    for(int l = 0; l < finalSpaces; l++) {
                        System.out.print(String.format("%s", " "));
                    }
                }
            });
            System.out.println();
            spaces -= 1;
        }
    }

    /** method that determine level of node
     * @param start
     * @param level
     */
    public void initNodes(Node start, int level){
        if(level == 0) {
            this.nodes.add(new PrintNode(start, level++));
        }
        for(Node node : start.getChildren()){
            if(node != null) {
                this.nodes.add(new PrintNode(node, level));
                if (node.getChildren().size() > 0) {
                    this.initNodes(node, level + 1);
                }
            }
        }
    }

}
