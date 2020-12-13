package xyz.molzhao;

/**
 * 抽象聚合接口，新增聚合类实现该接口：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
 */
public interface Aggregate {
    void add(Object o);

    void remove(Object o);

    Iterator getIterator();
}
