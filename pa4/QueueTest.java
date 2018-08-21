/**
 * File: QueueTest.java
 * Name: Sophia Tacderas
 * Due: 11/18/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa4
 * Purpose: Test Client for the Queue class.
 * Borrows/modifies code from: IntegerQueueTest.java
 */

public class QueueTest {

    public static void main(String[] args) {

        Queue testA = new Queue();
        System.out.println("testA length after new Queue(): " + testA.length());
        System.out.println("testA isEmpty() = " + testA.isEmpty() + "\n");

        // QueueEmptyException tests
        System.out.println("testA dequeue() on empty queue:");
        try{
            testA.dequeue();
        }catch(QueueEmptyException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption\n");
        }
        System.out.println("testA dequeueAll() on empty queue:");
        try{
            testA.dequeueAll();
        }catch(QueueEmptyException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption\n");
        }
        System.out.println("testA peek() on empty queue:");
        try{
            testA.peek();
        }catch(QueueEmptyException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption\n");
        }

        testA.enqueue(5);
        System.out.println("Enqueue 5 in testA, total numItems = " + testA.length());
        System.out.println("testA isEmpty() = " + testA.isEmpty());
        testA.enqueue(3);
        System.out.println("Enqueue 3 in testA, total numItems = " + testA.length());
        testA.enqueue(9);
        System.out.println("Enqueue 9 in testA, total numItems = " + testA.length());
        testA.enqueue(7);
        System.out.println("Enqueue 7 in testA, total numItems = " + testA.length());
        testA.enqueue(8);
        System.out.println("Enqueue 8 in testA, total numItems = " + testA.length());

        System.out.println("Complete Queue testA: " + testA);
        System.out.println("peek() at front of testA = " + testA.peek());
        System.out.println("dequeue()testA = " + testA.dequeue());
        System.out.println("dequeue()testA = " + testA.dequeue());
        System.out.println("dequeue()testA = " + testA.dequeue());
        System.out.println("peek() at front of testA = " + testA.peek());
        System.out.println("Complete testA: " + testA);
        System.out.println("testA toString(): " + testA.toString() + "\n");

        Queue testB = new Queue();
        System.out.println("testB length after new Queue(): " + testB.length());
        System.out.println("testA isEmpty() = " + testA.isEmpty());
        System.out.println("testB isEmpty() = " + testB.isEmpty() + "\n");

        testB.enqueue(7);
        System.out.println("Enqueue 7 in testB, total numItems = " + testB.length());
        testB.enqueue(8);
        System.out.println("Enqueue 8 in testB, total numItems = " + testB.length() + "\n");

        System.out.println("testA toString(): " + testA.toString());
        System.out.println("testB toString(): " + testB.toString());
        System.out.println("Is testA = testB? " + testA.equals(testB) + "\n");

        testA.enqueue(12);
        System.out.println("Enqueue 12 in testA, total numItems = " + testA.length());
        testB.enqueue(13);
        System.out.println("Enqueue 13 in testB, total numItems = " + testB.length()+ "\n");

        System.out.println("Complete testA: " + testA);
        System.out.println("Complete testB: " + testB);
        System.out.println("Is testA = testB? " + testA.equals(testB) + "\n");

        System.out.println("dequeueAll() testA:");
        testA.dequeueAll();
        System.out.println("testA toString(): " + testA.toString());
        System.out.println("testA isEmpty() = " + testA.isEmpty());
        System.out.println("dequeueAll() testB:");
        testB.dequeueAll();
        System.out.println("testB toString(): " + testB.toString());
        System.out.println("testB isEmpty() = " + testB.isEmpty());

    }

}
