/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.intelie.challenges.impl;

import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 *
 * @author raphael ribeiro <ribeiro.crn@gmail.com>
 */
public class EventIteratorImplTest {

    private Event event_1, event_2, event_3, event_4, event_5;

    @Before
    public void setUp() {

        event_1 = new Event("type_event_1", 10);
        event_2 = new Event("type_event_1", 12);
        event_3 = new Event("type_event_2", 11);
        event_4 = new Event("type_event_1", 17);
        event_5 = new Event("type_event_3", 4);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of moveNext method, of class EventIteratorImpl.
     */
    @Test
    public void testMoveNext() {
        System.out.println("moveNext");
        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);

        EventIterator iterator = instance.query("type_event_1", 10L, 20L);

        assertTrue(iterator.moveNext());
        assertEquals(event_1, iterator.current());
        assertFalse(iterator.moveNext());
    }

    /**
     * Test of current method, of class EventIteratorImpl.
     */
    @Test
    public void testCurrent() {
        System.out.println("current");
        String type = "type_event_1";

        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);

        EventIterator iterator = instance.query(type, 10L, 20L);

        assertTrue(iterator.moveNext());
        assertEquals(event_1, iterator.current());
    }

    /**
     * Test of remove method, of class EventIteratorImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);
        instance.insert(event_2);
        EventIterator iterator = instance.query("type_event_1", 0L, 23L);

        IllegalStateException assertThrows = assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });
    }

}
