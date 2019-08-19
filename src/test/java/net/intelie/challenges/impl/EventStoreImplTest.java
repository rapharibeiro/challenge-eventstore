/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.intelie.challenges.impl;

import java.util.ArrayList;
import java.util.List;
import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author raphael ribeiro <ribeiro.crn@gmail.com>
 */
public class EventStoreImplTest {

    private Event event_1, event_2, event_3, event_4, event_5;

    public EventStoreImplTest() {
    }

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
     * Test of insert method, of class EventStoreImpl.
     */
    @Test
    public void testInsert() {
        System.out.println("testInsert");

        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);
        instance.insert(event_2);
        instance.insert(event_3);
        instance.insert(event_4);

        assertEquals(4, instance.size());
    }

    /**
     * Test of removeAll method, of class EventStoreImpl.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("testRemoveAll");
        String type = "type_event_1";
        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);
        instance.insert(event_2);
        instance.insert(event_3);
        instance.insert(event_4);
        instance.removeAll(type);

        assertEquals(1, instance.size());
    }

    /**
     * Test of query method, of class EventStoreImpl.
     */
    @Test
    public void testQuery() {
        System.out.println("testQuery");
        String type = "type_event_1";

        List<Event> eList = new ArrayList<>();
        eList.add(event_1);
        eList.add(event_2);
        eList.add(event_4);

        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);
        instance.insert(event_2);
        instance.insert(event_3);
        instance.insert(event_4);

        EventIterator iterator = instance.query(type, 10L, 18L);
        int count = 0;
        while (iterator.moveNext()) {
            System.out.println(iterator.current().getType());
            count++;
        }
        assertEquals(count, eList.size());
    }

    /**
     * Test of size method, of class EventStoreImpl.
     */
    @Test
    public void testSize() {
        System.out.println("testSize");

        EventStoreImpl instance = new EventStoreImpl();
        instance.insert(event_1);
        instance.insert(event_2);
        instance.insert(event_3);
        instance.insert(event_4);

        assertEquals(4, instance.size());
    }
}
