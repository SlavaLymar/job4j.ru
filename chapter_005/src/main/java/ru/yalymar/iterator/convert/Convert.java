package ru.yalymar.iterator.convert;

import java.util.Iterator;

public interface Convert {

    Iterator <Integer> convert(Iterator <Iterator <Integer>> it);
}
