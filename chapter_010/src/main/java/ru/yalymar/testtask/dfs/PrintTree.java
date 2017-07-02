package ru.yalymar.testtask.dfs;

import java.util.ArrayList;
import java.util.List;

public class PrintTree {

    private Tree tree;
    private List<PrintNode> nodes;

    public PrintTree(Tree tree) {
        this.tree = tree;
        this.nodes = new ArrayList<>();
    }

    public class PrintNode{

        public Node node;
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
