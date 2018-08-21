/**
 * File: QueueEmptyException.java
 * Name: Sophia Tacderas
 * ID: 1465379
 * Due: 11/18/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa4
 * Purpose: Throws exception if trying to peek or dequeue on an empty queue.
 * Borrowed from Examples/Lecture/IntegerQueueADT/Array/QueueEmptyException.java
 * Subclass of RuntimeException
 */
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException(String s){
        super(s);
    }
}