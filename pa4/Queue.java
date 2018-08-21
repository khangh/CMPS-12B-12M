/**
 * File: Queue.java
 * Name: Sophia Tacderas
 * Due: 11/18/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa4
 * Purpose: Implements the Queue interface with 7 ADT operations.
 * Borrows/modifies code from: IntegerQueue.java
 */

import java.lang.Object;

public class Queue implements QueueInterface {

    // private inner Node class
    private class Node {
        Object object;
        Node next; // pointer to next Node

        Node(Object o) {
            object = o;
            next = null;
        }
    }

    // Fields for the Queue class
    private Node front; // reference to first Node in Queue
    private int numItems; // number of items in this Dictionary

    // Queue()
    // default constructor for the Queue class
    public Queue() {
        front = null;
        numItems = 0;
    }

    /***** 7 ADT Operations *****/

    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty() { return (numItems == 0); }

    // length()
    // pre: none
    // post: returns the length of this Queue.
    public int length() { return numItems; }

    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    public void enqueue(Object newItem) {
        Node N = new Node(newItem);
        if (isEmpty()) {
            front = N;
        } else {
            Node f = front;

            for (int ctr = 1; ctr <= numItems; ctr++) {
                if (f.next != null) {
                    f = f.next;
                }
            }
            f.next = N;
        }
        numItems++;
    }

    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException {
        if (numItems == 0) {
            throw new QueueEmptyException("cannot dequeue() empty queue");
        }
        Node N = front;
        Object returnObj = front.object;
        front = N.next;
        numItems--;
        return returnObj;
    }

    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException {
        if (numItems == 0) {
            throw new QueueEmptyException("cannot peek() empty queue");
        }
        Object returnObj = front.object;
        return returnObj;
    }

    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException {
        if (numItems == 0) {
            throw new QueueEmptyException("cannot dequeueAll() empty queue");
        }
        front = null;
        numItems = 0;
    }

    // toString()
    // overrides Object's toString() method
    public String toString() {
        Node N = front;
        String s = "";
        for (int ctr = 1; ctr <= numItems; ctr++) {
            s += N.object + " ";
            N = N.next;
        }
        return s;
    }

    /***** end 7 ADT Operations *****/

    // equals()
    // pre: none
    // post: returns true if this Queue matches rhs, false otherwise
    // Overrides Object's equals() method
    public boolean equals(Object rhs) {
        boolean eq = false;
        Queue R = null;
        Node N = null;
        Node M = null;

        if(rhs instanceof Queue) {
            R = (Queue) rhs;
            eq = ( this.numItems == R.numItems );

            N = this.front;
            M = R.front;
            while(eq && N!=null) {
                eq = (N.object == M.object);
                N = N.next;
                M = M.next;
            }
        }
        return eq;
    }

}
