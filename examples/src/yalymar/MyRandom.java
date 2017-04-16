package yalymar;

import java.util.Random;

public class MyRandom {

    public static void main(String[] args) {

        int x;
        for (int i = 0; i <20; i++) {
            x = new Random().nextInt(10);
            System.out.println(x);
        }

    }
}
