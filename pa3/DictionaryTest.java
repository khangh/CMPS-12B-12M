/**
 * File: DictionaryTest.java
 * Name: Sophia Tacderas, Maricris Bonzo
 * ID: 1465379, 1319731
 * Due: 11/4/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa3
 * Purpose: Tests the Dictionary interface as well as the ADT operations.
 * Based off of DictionaryClient.java.
 * Borrows code from IntegerListTest.java.
 */

public class DictionaryTest {
    public static void main(String[] args) {

        // New constructor
        Dictionary myDict = new Dictionary();

        System.out.println("isEmpty() = " + myDict.isEmpty()); // should be true
        System.out.println("Number of items: " + myDict.size() + "\n"); // should be 0

        myDict.insert("1", "First");
        System.out.println("Inserted first item.");
        System.out.println("isEmpty() = " + myDict.isEmpty()); // should be false
        System.out.println("Number of items: " + myDict.size() + "\n"); // should be 1

        myDict.insert("2", "Second");
        System.out.println("Inserted second item.");
        System.out.println("isEmpty() = " + myDict.isEmpty()); // should be false
        System.out.println("Number of items: " + myDict.size() + "\n"); // should be 2

        System.out.println("Inserting third item (2, Third) (should error).");
        try{
            myDict.insert("2", "Third"); // DuplicateKeyException error
        }catch(DuplicateKeyException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption\n");
        }

        myDict.insert("3", "Third");
        System.out.println("Inserted third item.");
        System.out.println("isEmpty() = " + myDict.isEmpty()); // should be false
        System.out.println("Number of items: " + myDict.size() + "\n"); // should be 3

        System.out.println("Inserting item with key 4 (should error).");
        try{
            myDict.delete("4"); // KeyNotFoundException error
        }catch(KeyNotFoundException e){
            System.out.println("Caught Exception " + e);
            System.out.println("Continuing without interruption\n");
        }

        myDict.delete("3");
        System.out.println("Deleted third item.");
        System.out.println("isEmpty() = " + myDict.isEmpty()); // should be false
        System.out.println("Number of items: " + myDict.size() + "\n"); // should be 2

        System.out.println("Value associated with key 1: " + myDict.lookup("1")); // should be "First"
        System.out.println("Value associated with key 2: " + myDict.lookup("2")); // should be "Second"
        System.out.println("Value associated with key 3: " + myDict.lookup("3") + "\n"); // should show null

        System.out.println("Printing out dictionary:");
        System.out.println(myDict.toString());
        /*
        Should display: 1 First
                        2 Second
         */

        myDict.makeEmpty();
        System.out.println("makeEmpty() was called.");
        System.out.println("Number of items: " + myDict.size()); // should be 0
        System.out.println("isEmpty() = " + myDict.isEmpty()); // should be true

    }
}
