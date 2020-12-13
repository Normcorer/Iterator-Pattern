package xyz.molzhao.impl;

import xyz.molzhao.Iterator;

import java.util.List;

/**
 * 具体迭代器实现类：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
 */
public class ConcreteIterator implements Iterator {
    private List<Object> list;

    private Integer index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object next() {
        return hasNext() ? list.get(++index) : null;
    }

    @Override
    public Object first() {
        index = 0;
        return list.get(index);
    }

    @Override
    public boolean hasNext() {
        return index < list.size() - 1;
    }
}
