/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.intelie.challenges.impl;

import java.util.Iterator;
import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;

/**
 *
 * @author raphael ribeiro <ribeiro.crn@gmail.com>
 */
public class EventIteratorImpl implements EventIterator {

    private final Iterator<Event> iterator;
    private Event event;

    public EventIteratorImpl(Iterator<Event> iterator) {
        this.iterator = iterator;
    }

    @Override
    public synchronized boolean moveNext() {
        if (this.iterator.hasNext()) {
            this.event = this.iterator.next();
            return true;
        }
        return false;
    }

    @Override
    public synchronized Event current() {
        if (this.event == null) {
            throw new IllegalStateException("function moveNext was never called or its last result was false");
        }
        return this.event;
    }

    @Override
    public synchronized void remove() {
        if (this.event == null) {
            throw new IllegalStateException("function moveNext was never called or its last result was false");
        }
        this.iterator.remove();
    }

    @Override
    public void close() throws Exception {
//        this.iterator = null;
    }
}
