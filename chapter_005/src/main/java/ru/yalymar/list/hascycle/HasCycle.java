package ru.yalymar.list.hascycle;

import ru.yalymar.list.simplelinkedlist.SimpleLinkedList;

/** @author slavalymar
 * @since 12.03.2017
 * @version 1
 * @param <T>
 */
public class HasCycle<T> extends SimpleLinkedList<T> implements IHasCycle{

    /** determines that have a cycle
     * @param first
     * @return boolean
     */
    @Override
    public boolean hasCycle(Node first) {
        boolean result = false;
        Node tmp = first;
        while(tmp != null && tmp.next != null){
            tmp = tmp.next;
            if(tmp.next != null && tmp.next.equals(first)){
                result = true;
                break;
            }
        }
        return result;
    }
}
