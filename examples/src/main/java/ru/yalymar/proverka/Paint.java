package ru.yalymar.proverka;
/**
 * Class Класс для построения пирамиды в псевдографике.
 * @author shkredr
 * @since 24.07.2017
 * @version 1.0
 */
public class Paint {
    /**Метод класса, рисующий пирамиду.
     * @param h - высота пирамиды.
     * @return result - возвращаемое строковое представление пирамиды.
     */
    public String piramid(int h) {
        /**Объект класса StringBuilder.
         * Используется для построения строкового представления пирамиды.
         */
        StringBuilder result = new StringBuilder("");
        /**Цикл for.
         * Цикл для создания пирамиды.
         */
        for (int i = 0; i <= h + 1; i += 2) {
            for (int j = 0; j < 3 - i; j += 2) {
                result.append(" ");
            }
            for (int k = 0; k <= i; k++) {
                result.append("^");
            }
            /**Метод перехода на новую строку.
             * Используется после формирования очередной строки.
             */
            System.getProperty("line.separator");
        }
        return result.toString();
    }
}