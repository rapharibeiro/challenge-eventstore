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
    
    /**
     * Using the keyword synchronization in the methods below is intended to protect data in cases of simultaneous access to manipulate or read a data. 
     * Since the method is synchronized, a thread will have to wait for the first thread to finish to perform the next execution. 
     * This protects you if a MoveNext event call is made on a remove event, that is, try to move on to the next event being removed. 
     * Using synchronization, one call will be executed and then the next, preventing both methods from reaching the critical region at the same time.
     * 
     */

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
        //nothing to do
    }
}
