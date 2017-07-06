package ru.yalymar.testtask.bfs;

/**
 * @author slavalymar
 * @since 02.07.2017
 * @version 1
 */
public class App {

    /** add values into tree
     * @param tree
     */
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
        App app = new App();
        Tree tree = new Tree();
        app.initTree(tree);
        PrintTree printTree = new PrintTree(tree);

        //print source tree
        printTree.initNodes(tree.getRoot(), 0);
        System.out.println("Source tree");
        printTree.print();

        //print mirror tree
        System.out.println("Mirror tree");
        BFS bfs = new BFS();
        bfs.bfs(tree.getRoot());
//        tree.setMirrorTree(tree.getRoot());
        printTree.getNodes().clear();
        printTree.initNodes(tree.getRoot(), 0);
        printTree.print();
    }
}
