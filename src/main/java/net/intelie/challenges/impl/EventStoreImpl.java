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

    private final ConcurrentSkipListMap<String, Event> eventStore = new ConcurrentSkipListMap<>();

    @Override
    public void insert(Event event) {
        this.eventStore.putIfAbsent(event.getId(), event);
    }

    @Override
    public void removeAll(String type) {
        this.eventStore.values().stream().filter((event) -> (event.getType().equals(type))).forEachOrdered((Event event) -> {
            this.eventStore.remove(event.getId());
        });
    }

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
