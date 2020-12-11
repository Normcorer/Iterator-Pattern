package xyz.molzhao.impl;

import xyz.molzhao.Aggregate;
import xyz.molzhao.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合实现类
 */
public class ConcreteAggregate implements Aggregate {
    private List<Object> aggregate = new ArrayList<>();

    @Override
    public void add(Object o) {
        aggregate.add(o);
    }

    @Override
    public void remove(Object o) {
        aggregate.remove(o);
    }

    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(aggregate);
    }
}
