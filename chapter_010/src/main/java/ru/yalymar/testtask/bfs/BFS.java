package ru.yalymar.testtask.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author slavalymar
 * @since 02.07.2017
 * @version 1
 */
public class BFS {

    /**
     * queue that save vertex in breadth order
     */
    private Queue<Node> queue = new LinkedList<>();

    /** traversing the tree in breadth
     * @param start
     */
    public void bfs(Node start){
        if(start.isVisited()){
            return;
        }
        this.queue.add(start);
        start.setVisited(true);
        while (!this.queue.isEmpty()){
            Node n = this.queue.poll();
            for (Node node : n.getChildren()) {
                if(node.isVisited()) {
                    continue;
                }
                this.queue.add(node);
                node.setVisited(true);
            }
            n.reflectNodes(n);
        }
    }



}
