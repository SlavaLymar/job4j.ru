package ru.yalymar.map.directory;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**@author slavalymar
 * @since 15.03.2017
 * @version 1
 * @param <K>
 * @param <V>
 */
public class Dictionary<K, V> {

    /**
     * default capacity of array (= 16)
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * maximum capacity
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * default load factor
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * buckets(array) of map
     */
    private DictionaryNode<K,V>[] table;

    /**
     * size of array
     */
    private int size;

    /**
     * custom load factor
     */
    private final float loadFactor;

    /**
     * next size of array when would be resize
     */
    private int threshold;

    public Dictionary() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public DictionaryNode<K, V>[] getTable() {
        return this.table;
    }

    /** return true if insert success
     * @param key
     * @param value
     * @return boolean
     */
    public boolean insert(K key, V value){
        return put(key, value) != null;
    }

    /** add map to array
     * @param key
     * @param value
     * @return V
     */
    private V put(K key, V value){
        DictionaryNode<K,V>[] tab; DictionaryNode<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash(key)]) == null)
            tab[i] = newNode(hash(key), key, value, null);
        else {
            DictionaryNode<K,V> e; K k;
            if (p.hash == hash(key) &&
                    ((k = p.key) == key || (key != null && key.equals(k)))){
                e = p;
            }
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash(key), key, value, null);
                        break;
                    }
                    if (e.hash == hash(key) &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!false || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        if (++size > threshold)
            resize();
        afterNodeInsertion(true);
        return null;
    }

    // Callbacks to allow LinkedHashMap post-actions
    void afterNodeInsertion(boolean evict) { }
    void afterNodeAccess(DictionaryNode<K,V> p) { }

    /** resize array
     * @return DictionaryNode
     */
    final DictionaryNode<K,V>[] resize() {
        DictionaryNode<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        DictionaryNode<K,V>[] newTab = (DictionaryNode<K,V>[])new DictionaryNode[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                DictionaryNode<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else { // preserve order
                        DictionaryNode<K,V> loHead = null, loTail = null;
                        DictionaryNode<K,V> hiHead = null, hiTail = null;
                        DictionaryNode<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /** calculate hash
     * @param key
     * @return int
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /** create new DictionaryNode
     * @param hash
     * @param key
     * @param value
     * @param next
     * @return DictionaryNode
     */
    DictionaryNode<K,V> newNode(int hash, K key, V value, DictionaryNode<K,V> next) {
        return new DictionaryNode<>(hash, key, value, next);
    }

    /** get value by key
     * @param key
     * @return V
     */
    public V get(K key) {
        DictionaryNode<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * Implements Map.get and related methods
     *
     * @param hash hash for key
     * @param key the key
     * @return the node, or null if none
     */
    final DictionaryNode<K,V> getNode(int hash, Object key) {
        DictionaryNode<K,V>[] tab; DictionaryNode<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    /** return true if delete happen
     * @param key
     * @return boolean
     */
    public boolean delete(K key){
        return remove(key) != null;
    }

    /** remove value by key
     * @param key
     * @return V
     */
    public V remove(K key) {
        DictionaryNode<K,V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    /** remove value by key
     * @param hash
     * @param key
     * @param value
     * @param matchValue
     * @param movable
     * @return Dictionary
     */
    final DictionaryNode<K,V> removeNode(int hash, Object key, Object value,
                               boolean matchValue, boolean movable) {
        DictionaryNode<K,V>[] tab; DictionaryNode<K,V> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            DictionaryNode<K,V> node = null, e; K k; V v;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key ||
                                    (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);

            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                    (value != null && value.equals(v)))) {
               if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                --size;
                //afterNodeRemoval(node);
                return node;
            }
        }
        return null;
    }

    // class that describe a map
    static class DictionaryNode<K, V> implements Map.Entry<K, V>{

        /**
         * hash
         */
        final int hash;

        /**
         * key of a map
         */
        final K key;

        /**
         * value of a map
         */
        V value;

        /**
         * next map relatively current map
         */
        DictionaryNode<K,V> next;

        DictionaryNode(int hash, K key, V value, DictionaryNode<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        /** calculate hash of a map
         * @return int
         */
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        /** set new value
         * @param newValue
         * @return V
         */
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        /** checked equals
         * @param o
         * @return boolean
         */
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    public DictionaryIterator getIterator(){
        return new DictionaryIterator();
    }

    // class that describe iterator
    public class DictionaryIterator{

        DictionaryNode<K,V> next;        // next entry to return
        DictionaryNode<K,V> current;     // current entry
        int index;             // current slot

        DictionaryIterator() {
            DictionaryNode<K,V>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        /** return true if in this index has a map
         * @return boolean
         */
        public final boolean hasNext() {
            return next != null;
        }

        /**
         * @return DictionaryNode
         */
        final DictionaryNode<K,V> nextNode() {
            DictionaryNode<K,V>[] t;
            DictionaryNode<K,V> e = next;
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }
    }
}
