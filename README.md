## 前言
迭代器模式其实在我们平时开发中，经常会用到，但是我们并不会自己去写一个迭代器，通常是调用java自带的迭代器，只有在自己想要设计一种新的数据结构的时候，可能会创建对应的迭代器，即便如此，我们也要了解迭代器是怎么实现的，它解决了什么问题，好奇心驱使我去学习了一下。

**解决的问题：**
为了解决在平时开发中，经常要访问一个聚合对象中的各个元素，如“数据结构”中的链表遍历，通常的做法是将链表的创建和遍历都放在同一个类中，但这种方式不利于程序的扩展，如果要更换遍历方法就必须修改程序源代码，这违背了 “开闭原则”的问题。
***

## 定义
提供一个对象来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。迭代器模式是一种对象行为型模式

***
## 结构

**迭代器模式主要包含以下角色：**
1. 抽象聚合（Aggregate）角色：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
2. 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
3. 抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法。
4. 具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。

![迭代器模式结构图](https://molzhao-pic.oss-cn-beijing.aliyuncs.com/2020-12-13/%E8%BF%AD%E4%BB%A3%E5%99%A8%E6%A8%A1%E5%BC%8F.png)
***

## 特点

**优点：**
1. 访问一个聚合对象的内容而无须暴露它的内部表示。
2. 遍历任务交由迭代器完成，这简化了聚合类。
3. 它支持以不同方式遍历一个聚合，甚至可以自定义迭代器的子类以支持新的遍历。
4. 增加新的聚合类和迭代器类都很方便，无须修改原有代码。
5. 封装性良好，为遍历不同的聚合结构提供一个统一的接口。


**缺点：**
1. 增加了类的个数，一定程度上增加了程序的复杂度
***

## 案例

```java
package xyz.molzhao;

/**
 * 抽象聚合接口，新增聚合类实现该接口：定义存储、添加、删除聚合对象以及创建迭代器对象的接口。
 */
public interface Aggregate {
    void add(Object o);

    void remove(Object o);

    Iterator getIterator();
}
```

```java
package xyz.molzhao.standard;

package xyz.molzhao.impl;

import xyz.molzhao.Aggregate;
import xyz.molzhao.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合实现类：实现抽象聚合类，返回一个具体迭代器的实例。
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
```

```java
package xyz.molzhao;

/**
 * 抽象迭代器接口：定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法。
 */
public interface Iterator {
    Object next();

    Object first();

    boolean hasNext();

}

```

```java
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
```

```java
package xyz.molzhao;

import org.junit.Test;
import xyz.molzhao.impl.ConcreteAggregate;

import static org.junit.Assert.*;

public class IteratorPatternTest {
    @Test
    public void testIterator() {
        // Arrange
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("item1");
        aggregate.add("item2");
        aggregate.add("item3");
        aggregate.add("item4");
        aggregate.add("item5");
        Iterator iterator = aggregate.getIterator();

        // Act
        boolean result = iterator.hasNext();

        // Assert
        assertTrue(result);
        assertEquals("item1", iterator.next());
        assertEquals("item2", iterator.next());
        assertEquals("item3", iterator.next());
        assertEquals("item4", iterator.next());
        assertEquals("item5", iterator.next());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
        assertEquals("item1", iterator.first());
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testIterator_ergodic() {
        // Arrange
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("item1");
        aggregate.add("item2");
        aggregate.add("item3");
        aggregate.add("item4");
        aggregate.add("item5");
        Iterator iterator = aggregate.getIterator();

        StringBuilder result = new StringBuilder();

        // Act
        while (iterator.hasNext()) {
            result.append(iterator.next());
        }

        // Assert
        assertEquals("item1item2item3item4item5", result.toString());
    }
}
```

***

> 该文章参考的原文链接 http://c.biancheng.net/view/1395.html
> 如果有小伙伴，想要一起交流学习的，欢迎添加博主微信。

![weChat](https://molzhao-pic.oss-cn-beijing.aliyuncs.com/Common/WeChat.png)