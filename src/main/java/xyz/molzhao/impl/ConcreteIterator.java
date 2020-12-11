package xyz.molzhao.impl;

import xyz.molzhao.Iterator;

import java.util.List;

/**
 * 具体迭代器实现类
 */
public class ConcreteIterator implements Iterator {
    private List<Object> list;

    private Integer index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            return list.get(++index);
        } else {
            return null;
        }

    }

    @Override
    public Object first() {
        index = 0;
        return list.get(index);
    }

    @Override
    public boolean hasNext() {
        return index < list.size() -1;
    }
}
