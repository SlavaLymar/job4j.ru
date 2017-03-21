package ru.yalymar.tree.binarysearch;

import java.util.*;

/**@author slavalymar
 * @since 17.03.2017
 * @version 1
 * @param <K>
 * @param <V>
 */
public class SimpleTree<K, V> {

    /**
     * root of tree
     */
    private Leaf root;

    /**
     * list of leaves
     */
    private List<Leaf> leaves = new LinkedList<>();

    public Leaf getRoot() {
        return this.root;
    }

    /**method that add leaf or root
     * @param leaf
     */
    public void addChild(Leaf leaf){
        if(this.root != null) {
            this.move(this.root, leaf);
        }
        else {
            this.root = leaf;
            this.root.addToList(leaf);
            this.leaves.add(leaf);
        }
    }

    /**method that find root
     * @param current
     * @param leaf
     * @return Leaf
     */
    private Leaf move(Leaf current, Leaf leaf){
        int i = current.compareTo(leaf);
        if(i == 0) {
            this.addToList(current, leaf);
            return current;
        }
        else if(i < 0){
            if(current.right != null) this.move(current.right, leaf);
            else this.insertLeaf(i, current, leaf);
        }
        else {
            if(current.left != null) {
                this.move(current.left, leaf);
            }
            else this.insertLeaf(i, current, leaf);
        }
        return current;
    }

    /**add to linkedlist if keys of leaves equals
     * @param current
     * @param leaf
     */
    private void addToList(Leaf current, Leaf leaf) {
        current.addToList(leaf);
    }

    /**add leaf
     * @param i
     * @param current
     * @param leaf
     */
    private void insertLeaf(int i, Leaf current, Leaf leaf){
        if(i < 0) {
            current.right = new Leaf(leaf.getKey(), leaf.getValue(), current);
            this.leaves.add(current.right);
        }
        else {
            current.left = new Leaf(leaf.getKey(), leaf.getValue(), current);
            this.leaves.add(current.left);
        }
    }

    /**return children of root
     * @param key
     * @return List
     */
    public List<Leaf> getChildren(K key){
        List<Leaf> result;
        if(this.root != null) {
            result = this.findChildrenByKey(this.root, key);
        }
        else result = new ArrayList<>(Arrays.asList(this.root));
        return result;
    }

    /**looking for children by key
     * @param current
     * @param key
     * @return List
     */
    private List<Leaf> findChildrenByKey(Leaf current, K key) {
        List<Leaf> result = null;
        int index = 0;
        int i = current.compareTo(new Leaf(key, null));
        if(i == 0) {
            result = new ArrayList<>();
            if(current.left != null) result.add(index++, current.left);
            if(current.right != null) result.add(index++, current.right);
            return result;
        }
        else if(i > 0){
            if(current.right != null) result = this.findChildrenByKey(current.right, key);
        }
        else {
            if(current.left != null) result = this.findChildrenByKey(current.left, key);
        }
        return result;
    }

    /** find leaf by key
     * @param current
     * @param key
     * @return Leaf
     */
    public Leaf findByKey(Leaf current, K key) {
        Leaf result = null;
        int i = current.compareTo(new Leaf(key, null));
        if(i == 0) {
            result = current;
            return result;
        }
        else if(i < 0){
            if(current.right != null) result = this.findByKey(current.right, key);
        }
        else {
            if(current.left != null) result = this.findByKey(current.left, key);
        }
        return result;
    }

    public boolean isBalanced(){
        boolean result = true;
        for(Leaf leaf : this.leaves){
            int i = this.getChildrenByLeaf(leaf).size();
            if(i != 2 && i != 0){
                result = false;
                return result;
            }
        }
        return result;
    }

    /**return children of current leaf
     * @param current
     * @return List
     */
    public List<Leaf> getChildrenByLeaf(Leaf current){
        List<Leaf> result;
        int index = 0;
        result = new ArrayList<>();
        if(current.left != null) result.add(index++, current.left);
        if(current.right != null) result.add(index++, current.right);
        return result;
    }

    /** simple search leaf by key
     * @param key
     * @return Leaf
     */
    public Leaf simpleSearch(K key){
        Leaf result = null;
        for(Leaf leaf : this.leaves){
            if(leaf != null && leaf.getKey().equals(key)){
                result = leaf;
                return result;
            }
        }
        return result;
    }

    // class that describes leaf
    public class Leaf implements Comparable<Leaf>, Iterator<V>{

        /**
         * key
         */
        K key;

        /**
         * value
         */
        V value;

        /**
         * parent of leaf
         */
        Leaf papa;

        /**
         * left child if leaf
         */
        Leaf left;

        /**
         * right child if leaf
         */
        Leaf right;

        /**
         * list of leaf
         */
        List<V> list = new LinkedList<>();

        /**
         * indexes of iterator and list
         */
        int cursor = 0, index = 0;

        public Leaf(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private Leaf(K key, V value, Leaf papa) {
            this.key = key;
            this.value = value;
            this.papa = papa;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public List<V> getList() {
            return list;
        }

        public int getCursor() {
            return cursor;
        }

        /** add value to list of leaf
         * @param leaf
         */
        public void addToList(Leaf leaf){
            this.list.add(index++, leaf.getValue());
        }

        /** compare couple leaves
         * @param l
         * @return int
         */
        @Override
        public int compareTo(Leaf l) {
            if(this.key.hashCode() == l.key.hashCode()) return 0;
            if(this.key.hashCode() > l.key.hashCode()) return 1;
            return -1;
        }

        /** return true if has a value in the list of leaf
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return this.cursor < this.list.size();
        }

        /** return value
         * @return V
         */
        @Override
        public V next() {
            if(this.hasNext()) return this.list.get(cursor++);
            return null;
        }

    }


}


