package ru.yalymar.testtask.service;


/**
 * @author slavalymar
 * @since 17.07.2017
 * @version 1
 */
public interface CompareLines {

    /**
     * compares the lines depending on the lexical order.
     * if (line1 < line2) = -1, if (line1 > line2 = 1, else 0)
     *
     * @param line1
     * @param line2
     * @return int
     */
    default int compareLines(String line1, String line2) {
        int result = 0;
        int current = 0;
        for (int i = 0; i < line1.length() && i < line2.length(); i++) {
            current = i;
            char c1 = line1.charAt(i);
            char c2 = line2.charAt(i);
            if (c1 == c2) {
                continue;
            } else if (c1 < c2) {
                result = -1;
                break;
            } else {
                result = 1;
                break;
            }
        }

        // if lines are equals check length of them.
        // if length > current that mean the line is larger then the other one
        if (result == 0) {
            if (line1.length() - 1 > current) {
                result = 1;
            } else if (line2.length() - 1 > current) {
                result = -1;
            }
        }
        return result;
    }
}
