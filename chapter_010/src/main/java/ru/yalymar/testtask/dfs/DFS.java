package ru.yalymar.testtask.dfs;


public class DFS {

    private void initTree(Tree tree){
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(30);
        tree.add(12);
        tree.add(7);
        tree.add(4);
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        Tree tree = new Tree();
        dfs.initTree(tree);
        PrintTree printTree = new PrintTree(tree);
        printTree.initNodes(tree.getRoot(), 0);
        System.out.println("Source tree");
        printTree.print();
        System.out.println("Mirror tree");
        tree.setMirrorTree(null);
        printTree.print();
    }
}
