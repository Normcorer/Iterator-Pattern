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
