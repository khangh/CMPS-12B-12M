/**
 * File: Dictionary.java
 * Name: Sophia Tacderas, Maricris Bonzo
 * Due: 11/4/16, 10 pm
 * Class: CMPS 12B
 * Assignment: pa3
 * Purpose: Implements the Dictionary interface with 7 ADT operations.
 * Borrows/modifies code from: IntegerList.java
 */

public class Dictionary implements DictionaryInterface {

    // private inner Node class
    private class Node {

        // key is distinct/unique
        String key;
        // value may repeat
        String value;
        // pointer to next Node
        Node next;

        Node(String x, String y) {
            key = x;
            value = y;
            next = null;
        }

    }

    // Fields for the Dictionary class
    private Node head; // reference to first Node in Dictionary
    private int numItems; // number of items in this Dictionary

    // Constructor for the Dictionary class
    public Dictionary(){
        head = null;
        numItems = 0;
    }

    // Private method that returns a reference to the Node containing its argument key.
    // Otherwise, returns null if that Node does not exist.
    // Helps for implementation of insert(), delete() and lookup().
    private Node findKey(String key) {
        Node N = head;
        if(N != null) {
            for (int ctr = 1; ctr <= numItems; ctr++) {
                if (N.key.equals(key)) {
                    return N;
                } else {
                    N = N.next;
                }
            }
        }
        return null;
    }


    /***** 7 ADT Operations *****/

    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty() {
        if (numItems==0){
            return true;
        }
        else {
            return false;
        }
    }

    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    public int size() {
        return numItems;
    }

    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    public String lookup(String key) throws KeyNotFoundException{
        // use findKey() to find the Node
        Node N = findKey(key);

        // if returned value of findKey() is not null then it was found
        if (N != null) {
            for (int ctr = 1; ctr <= numItems; ctr++) {
                if (N.key.equals(key)) {
                    // return value when found
                    return N.value;
                } else {
                    N = N.next;
                }
            }
        }

        // if no such pair exists in the Dictionary then return null
        return null;
    }

    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    public void insert(String key, String value) throws DuplicateKeyException {
        if(lookup(key) == null) {
            // this means arguments are not duplicate
            Node N = head;

            if (N == null) {
                // list is empty so this is first item
                Node newNode = new Node(key, value);
                newNode.next = head;
                head = newNode;
            } else {
                // insert item at the end of list
                for (int ctr = 1; ctr < numItems; ctr++) {
                    N = N.next;
                }
                N.next = new Node(key, value);
            }
            numItems++;

        } else {
            throw new DuplicateKeyException(
                    "cannot insert duplicate keys " + key);
        }
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    public void delete(String key) throws KeyNotFoundException {
        if (findKey(key) == null) {
            throw new KeyNotFoundException(
                    "Dict. err: delete() cannot delete non-existent key.");
        }
        if (findKey(key) == head) {
            Node N = head;
            head = head.next;
            N.next = null;
        } else {
            Node N = findKey(key);
            Node prev = head;
            Node temp = head.next;
            while (temp != N) {
                temp = temp.next;
                prev = prev.next;
            }
            prev.next = N.next;
            N.next = null;
        }
        numItems--;
    }

    // makeEmpty()
    // pre: none
    public void makeEmpty() {
        head = null;
        numItems = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node N = head;

        for( ; N!=null; N=N.next){
            sb.append(N.key).append(" ").append(N.value).append("\n");
        }
        return new String(sb);
    }

}
