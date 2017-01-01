package ru.maxfromthird;
import ru.max.Max;

/**
 * @author slavalymar
 * @since 01.01.2017
 * @version 1
 */
public class MaxFromThird {

    /**
     * @param first
     * @param second
     * @param third
     * @return maxFST
     */
    int maxThird(int first, int second, int third){
        Max m = new Max();
        int maxFST = m.max(m.max(first, second), third);
        return maxFST;
    }
}
