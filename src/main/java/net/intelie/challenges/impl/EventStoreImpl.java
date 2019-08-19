/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.intelie.challenges.impl;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;
import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import net.intelie.challenges.EventStore;

/**
 *
 * @author raphael ribeiro <ribeiro.crn@gmail.com>
 */
public class EventStoreImpl implements EventStore {

    
    /**
     * Initially I considered using the ConcurrentHashMap structure, as well as being a thread-safe structure has the order to put and get multithreaded in O (1). 
     * But I chose ConcurrentSkipListMap, which has the order of O (log n) (which is not much of a difference) because it offers a more efficient ordered search, 
     * and in an evolution to priority-ordered events, this functionality becomes more appropriate.
     */
    
    /**
     * See more in: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentSkipListMap.html
     * 
     */
    
    
    private final ConcurrentSkipListMap<String, Event> eventStore = new ConcurrentSkipListMap<>();

    /**
     * Insert a event into the Map using method putIfAbsent
     * putIfAbsent: If the specified key is not already associated with a value, associate it with the given value. 
     */
    
    @Override
    public void insert(Event event) {
        this.eventStore.putIfAbsent(event.getId(), event);
    }
    
    /**
     * Functional logic to remove all types of a given event
     *  
     */

    @Override
    public void removeAll(String type) {
        this.eventStore.values().stream().filter((event) -> (event.getType().equals(type))).forEachOrdered((Event event) -> {
            this.eventStore.remove(event.getId());
        });
    }

    /**
     * Created a filter so that the iterator uses an event type with a certain start and end time.
     *  
     */
    @Override
    public EventIterator query(String type, long startTime, long endTime) {
        Iterator<Event> iterator = eventStore.values().stream().filter(event -> type.equals(event.getType()) && startTime <= event.getTimestamp() && endTime > event.getTimestamp()).iterator();
        return new EventIteratorImpl(iterator);
    }

    @Override
    public int size() {
        return this.eventStore.size();
    }
}
