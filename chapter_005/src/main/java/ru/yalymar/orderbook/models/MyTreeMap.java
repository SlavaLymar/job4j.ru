package ru.yalymar.orderbook.models;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author slavalymar
 * @since 23.03.2017
 * @version 1
 */
public class MyTreeMap<K, V> extends TreeMap<K, V> {

    /** return lowest Map.Entry
     * @return Map.Entry
     */
    public Map.Entry<K, V> getLow() {
        return this.firstEntry();
    }

    /** return highest Map.Entry
     * @return Map.Entry
     */
    public Map.Entry<K, V> getHigh(){
        return this.lastEntry();
    }
}
