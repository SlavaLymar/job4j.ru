package ru.yalymar.iterator.convert;

import java.util.ArrayList;
import java.util.Iterator;

public class ConvertIterators implements Convert{

    private GeneralizedIterator generalizedIterator
            = new GeneralizedIterator(new ArrayList<>());
    private Iterator<Iterator<Integer>> it;

    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.it = it;
        if(this.it != null){
            while(this.it.hasNext()) {
                Iterator<Integer> tmpIterator = this.it.next();
                while (tmpIterator.hasNext()) {
                    this.generalizedIterator.getIntegers().add(tmpIterator.next());
                }
            }
        }
        return this.generalizedIterator;
    }
}
