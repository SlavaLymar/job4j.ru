package yalymar;

import java.util.Iterator;

public class LinkedListTest {

    private static final int M = 5;

    public static void main(String[] args) {

        int[] origin = new int[]{10, 20, -2, 3, 15, 0, 2, 2, 19, -100};
        int[] a = new int[M];
        Integer[] visited = new Integer[a.length];

        for (int i = 0; i < a.length; i++){
            int min = origin[i];
            int index = i;
            for (int j = 0; j < origin.length - 1 ; j++){
                boolean exit = false;
                for(int v = 0; v < visited.length; v++){
                    if(visited[v] == null) break;
                    if(visited[v] == j) {
                        exit = true;
                        break;
                    }
                }
                if(exit) continue;
                int tmp;
                if(origin[j] < origin[j+1]){
                    tmp = origin[j];
                    index = j;
                }
                else {
                    tmp = origin[j+1];
                    index = j;
                }
                if(tmp < min){
                    min = tmp;
                }
            }
            a[i] = min;
            visited[i] = index;
        }

        for (int i : a) {
            System.out.println(i);
        }
    }

}
