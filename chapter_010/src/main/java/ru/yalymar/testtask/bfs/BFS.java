package ru.yalymar.testtask.bfs;

import java.util.Queue;

public class BFS {

    private Queue<Node> queue;

    public Queue<Node> getQueue() {
        return this.queue;
    }

    public void bfs(Node start){
        if(start.isVisited()){
            return;
        }
        this.queue.add(start);
        start.setVisited(true);
        while (!this.queue.isEmpty()){
            Node n = this.queue.poll();
            for (Node node : n.getChildren()) {
                if(n.isVisited()) continue;
                this.queue.add(node);
                node.setVisited(true);
            }
        }
    }



}
